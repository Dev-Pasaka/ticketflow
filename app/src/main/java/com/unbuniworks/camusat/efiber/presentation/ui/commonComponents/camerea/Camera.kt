package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.camerea

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Camera(
    navController: NavHostController,
    index: Int,
    imageIndex: Int,
    ticketInformationViewModel: TicketInformationViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    controller.cameraSelector =
                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }
                },

                ) {
                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    contentDescription = "Switch camere",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )

            }
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)

                )

            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
        ) {

            IconButton(
                enabled = false,
                onClick = {
                    scope.launch {
                        val bitmap = takePhoto(
                            context = context,
                            controller = controller,
                            lifecycleOwner = lifecycleOwner
                        )
                        bitmap?.let {

                            navController.popBackStack()
                        }
                    }

                }
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Take Photo",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                )

            }

        }
    }
    if (false){
        ProcessingPhoto(ticketInformationViewModel = ticketInformationViewModel)
    }
}

suspend fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    lifecycleOwner: LifecycleOwner
): Bitmap? = withContext(Dispatchers.Main) {
    return@withContext try {
        suspendCancellableCoroutine { continuation ->
            controller.takePicture(
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        super.onCaptureSuccess(image)

                        val originalBitmap = image.toBitmap()

                        val matrix = Matrix().apply {
                            postRotate(image.imageInfo.rotationDegrees.toFloat())
                        }

                        val rotatedBitmap = Bitmap.createBitmap(
                            originalBitmap,
                            0,
                            0,
                            originalBitmap.width,
                            originalBitmap.height,
                            matrix,
                            true
                        )

                        continuation.resume(rotatedBitmap) {
                            image.close() // Close the ImageProxy if coroutine is cancelled
                        }
                    }


                    override fun onError(exception: ImageCaptureException) {
                        super.onError(exception)
                        Log.e("Camera", "Could not take photo", exception)
                        continuation.resume(null) {
                            // Handle cancellation if needed
                        }
                    }
                }
            )
        }
    } catch (e: Exception) {
        Log.e("Camera", "Error capturing photo", e)
        null
    }
}