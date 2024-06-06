package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.app.Activity
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto

sealed class TicketInformationEvents() {
    data class SelectTemplate(val templateName:String):TicketInformationEvents()
    data object OpenOrCloseTemplatesBottomSheet:TicketInformationEvents()
    data object Refresh:TicketInformationEvents()
    data class SubmitEmailTemplate(val workOrderId:String,val templateId:String):TicketInformationEvents()
    data class PostWorkOrderTask(val workOrderTaskDto: PostWorkOrderTaskDto, val activity: Activity):TicketInformationEvents()
}