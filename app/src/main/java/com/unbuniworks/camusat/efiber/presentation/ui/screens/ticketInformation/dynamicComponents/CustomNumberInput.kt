package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomNumberInput(
    status:String,
    feature: Feature,
    index: Int,
    ticketInformationViewModel: TicketInformationViewModel
) {

    val placeHolderText = if (feature.availableQuantity == null) feature.name else
        "Available Qty: ${feature.availableQuantity}"
    Column {

        if (feature.availableQuantity != null){
            Text(
                text = feature.name,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)

            )
        }
        OutlinedTextField(
            placeholder = {
                Text(
                    text = placeHolderText,
                    color = Color.DarkGray,
                )
            },
            value = feature.value ?: "",
            onValueChange = {
                ticketInformationViewModel.updateNumberComponent(
                    index = index,
                    text = it,
                    inputType = feature.inputType
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 2.dp)
                .fillMaxWidth(),
        )
    }

}