package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UploadOrTakePhotoDialog(
    index: Int,
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

            // Photo picker.
            if (uri != null) {
                ticketInformationViewModel.selectImage(uri = uri, index = index)
                ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = "$index")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    var photoUri: Uri? by remember { mutableStateOf(null) }

    val resultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            photoUri?.let {
                bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                Log.e("Bitmap", bitmap.toString())
                if (bitmap != null) {
                    val uri = ticketInformationViewModel.getImageUriFromBitmap(
                        context = context,
                        bitmap = bitmap!!
                    )
                    Log.e("Uri", uri.toString())
                    ticketInformationViewModel.selectImage(index = index, uri = uri)
                    ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = "$index")
                }
            }
        }
    }

    Dialog(
        onDismissRequest = { ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = "$index") },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Surface(
                    onClick = {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = "upload image",
                            tint = colorResource(id = R.color.button_link_color),
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "Upload image",
                            color = colorResource(id = R.color.button_link_color)
                        )

                    }
                }
                Surface(
                    onClick = {
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        val photoFile: File? = createImageFile(context)
                        photoFile?.also {
                            photoUri = FileProvider.getUriForFile(
                                context,
                                "${context.packageName}.fileprovider",
                                it
                            )
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                            resultLauncher.launch(cameraIntent)
                        }
                    },
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo),
                            contentDescription = "Take photo",
                            tint = colorResource(id = R.color.button_link_color),
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "Take photo",
                            color = colorResource(id = R.color.button_link_color)
                        )

                    }
                }

            }
        }
    }

}



fun createImageFile(context: Context): File? {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return try {
        File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    } catch (ex: IOException) {
        null
    }
}