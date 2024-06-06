package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.EmailTemplates
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel


@Composable
fun SendEmailTemplate(
    ticketInformationViewModel: TicketInformationViewModel,
    status: String = "",
    emailTemplates: List<EmailTemplates> = emptyList(),
    templateName: String,
    isSelected: Boolean,
    containerColor: Color,
    textColor: Color,
    templatePosition: Int,
    onClick: () -> Unit,
    onCloseDropDown: () -> Unit,
    workOrderId: String
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        onClick = {
            onClick()
            expanded = !expanded
        },
        shape = RoundedCornerShape(5.dp),
        color = containerColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "$templatePosition. $templateName",
                        color = textColor,
                        fontSize = 14.sp
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            onClick()
                            expanded = !expanded
                        }) {
                            if (status == "complete") {
                                Surface(
                                    shape = CircleShape,
                                    color = Color.Green,
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Submitted",
                                        tint = Color.White,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = if (isSelected) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = if (isSelected) "Selected" else "Not selected",
                                    tint = Color.DarkGray
                                )
                            }
                        }
                    }
                }
                if (expanded) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                            onCloseDropDown()
                        },
                        offset = DpOffset(x = 0.dp,y = (300.dp)),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(0.8f)
                    ) {
                        emailTemplates.forEach { template ->
                            DropdownMenuItem(
                                text = { Text(template.name) },
                                onClick = {
                                        ticketInformationViewModel.event(
                                            TicketInformationEvents.SubmitEmailTemplate(
                                                workOrderId = workOrderId,
                                                templateId = template.id
                                            )
                                        )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


