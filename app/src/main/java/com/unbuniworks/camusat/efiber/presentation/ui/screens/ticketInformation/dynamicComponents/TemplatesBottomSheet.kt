package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesBottomSheet(
    isSpecialFeature:Boolean,
    featureName:String,
    status:String,
    workOrderId: String,
    sheetState: SheetState,
    buttonName: String,
    navController: NavHostController,
    openOrCloseBottomSheet: () -> Unit,
    features: List<Feature>,
    ticketInformationViewModel: TicketInformationViewModel,
) {

    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val scrollState  = rememberScrollState()
    Log.e("special", "$featureName, $isSpecialFeature")

    ModalBottomSheet(
        onDismissRequest = openOrCloseBottomSheet,
        shape = RectangleShape,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)

                .padding(4.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Render each feature based on its input type using a when statement
            features.forEachIndexed { index, feature ->
                when (feature.inputType) {

                    /** Render Text */
                    "Text" -> {
                        CustomTextFiled(
                            status = status,
                            label = feature.name,
                            value = feature.value ?: "",
                            onValueChange = {
                                ticketInformationViewModel.updateTextComponent(
                                    index = index,
                                    text = it,
                                    inputType = feature.inputType
                                )
                            },
                            index = index,
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    /** Render Number */
                    "Number" -> {
                        CustomNumberInput(
                            status = status,
                            index = index,
                            feature = feature,
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    /** Render Dropdown */
                    "Dropdown" -> {
                        CustomDropDown(
                            status = status,
                            feature = feature,
                            navController = navController,
                            index = index,
                            key = "$index",
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                    }

                    "Date" -> {
                        /** Render date  input */
                        DateInput(
                            status = status,
                            feature = feature,
                            index = index,
                            key = "$index",
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                    }

                    "Time" -> {

                        /** Render date  time */
                        CustomTimeInput(
                            feature = feature,
                            index = index,
                            key = "$index",
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    "Image" -> {
                        /** Render Image */
                        CustomImageUpload(
                            feature = feature,
                            index = index,
                            key = "$index",
                            navController = navController,
                            ticketInformationViewModel = ticketInformationViewModel
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    "GPS" -> {
                        Location(
                            index = index,
                            feature = feature,
                            ticketInformationViewModel = ticketInformationViewModel,
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }


                }
            }

            /** Render Button */
            if (ticketInformationViewModel.postingWorkOrderState.isLoading){
               Row(
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.Center,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Spacer(modifier = Modifier.height(16.dp))
                   CircularProgressIndicator(
                       color = colorResource(id = R.color.button_color),
                       strokeWidth = 3.dp,
                       strokeCap = StrokeCap.Butt
                   )
               }
            }else {

                CustomButton(
                    status = status,
                    buttonName = buttonName,
                    action = {
                        scope.launch {
                            ticketInformationViewModel.event(
                                TicketInformationEvents.PostWorkOrderTask(
                                    PostWorkOrderTaskDto(
                                        statusColor = ticketInformationViewModel.templatestatusColor,
                                        isSpecialFeature = isSpecialFeature,
                                        featureName = featureName,
                                        workOrderId = ticketInformationViewModel.workOrderId,
                                        taskId = ticketInformationViewModel.currentFeatureId,
                                        userId = SharedPreferenceRepositoryImpl().getString(
                                            Constants.userId, activity
                                        ) ?: "",
                                        features = ticketInformationViewModel.currentFeaturesList.value
                                    ),
                                    activity = activity
                                )
                            )
                        }
                    },
                    onCancel = { ticketInformationViewModel.openOrCloseCancelAlertDialog() }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}


