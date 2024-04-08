package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketInformationLowerSection(
    navController :NavHostController,
    ticketInformationViewModel: TicketInformationViewModel
) {
    val currentFeaturesList by ticketInformationViewModel.currentFeaturesList.collectAsState()



    val scope = rememberCoroutineScope()

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(
                count = ticketInformationViewModel.workOrderDetailState.data?.workOrderTasks?.size
                    ?: 0
            ) {
                val template =
                    ticketInformationViewModel.workOrderDetailState.data?.workOrderTasks?.get(it)
                val isSelected  =
                    ticketInformationViewModel.workOrderDetailState.selectedTemplate == template?.name

                Template(
                    status = template?.status ?: "",
                    templateName = template?.name ?: "",
                    isSelected = isSelected,
                    containerColor = if (isSelected) colorResource(id = R.color.light_blue) else Color.LightGray,
                    textColor = Color.DarkGray,
                    templatePosition = it + 1,
                    onclick = {
                        scope.launch {
                            ticketInformationViewModel.event(
                                TicketInformationEvents.SelectTemplate(template?.name ?: "")
                            )
                            ticketInformationViewModel.updateCurrentTemplates(
                                openOrCloseBottomSheet = true,
                                currentFeatures = template?.features ?: emptyList(),
                                buttonName = template?.buttonName ?: "",
                                featureId = template?.id ?: ""
                            )
                            ticketInformationViewModel.updateStatus(status = template?.status ?: "" )
                        }



                    }
                )




            }


        }

        if (ticketInformationViewModel.isBottomSheetOpen) {
            TemplatesBottomSheet(
                status = ticketInformationViewModel.selectedStatus,
                sheetState = sheetState,
                features = currentFeaturesList,
                ticketInformationViewModel = ticketInformationViewModel,
                workOrderId = ticketInformationViewModel.currentFeatureId,
                navController = navController,
                buttonName = ticketInformationViewModel.currentButtonName,
                openOrCloseBottomSheet = {
                    ticketInformationViewModel.openOrCloseBottomSheet()

                }
            )
        }

        if (ticketInformationViewModel.iscancelAlertDialogOpen){
            ExitDialogBoxNotification(
                icon = Icons.Default.Cancel,
                dialogTitle = "Are you sure you want to exit?" ,
                dialogText = "If you cancel before submitting changes will be lost.",
                onDismissRequest = { ticketInformationViewModel.openOrCloseCancelAlertDialog() },
                onConfirmation = {
                    ticketInformationViewModel.openOrCloseCancelAlertDialog()
                    ticketInformationViewModel.openOrCloseBottomSheet()
                })
        }
    }
}