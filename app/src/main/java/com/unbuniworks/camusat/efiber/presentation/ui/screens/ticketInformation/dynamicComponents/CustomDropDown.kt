package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomDropDown(
    feature: Feature,
    index: Int,
    key:String,
    navController: NavHostController,
    ticketInformationViewModel: TicketInformationViewModel,
    status: String,
) {

    var isDropdownOpen by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)

    ) {

        OutlinedTextField(
            readOnly = true,
            placeholder = {
                Text(
                    text = feature.name,
                    color = Color.DarkGray,
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isDropdownOpen = true
                        ticketInformationViewModel.selectDropDown(key = key)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop down",
                        tint = Color.DarkGray,
                    )
                }
            },
            value = feature.value ?: "",
            onValueChange = {

            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier
                .padding(vertical = 2.dp)
                .fillMaxWidth(),
        )

        if (isDropdownOpen && ticketInformationViewModel.currentDropDown == key) {
            DropdownMenu(
                expanded = true,
                onDismissRequest = {
                    isDropdownOpen = false
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            ) {

               feature.options.forEach {
                   DropdownMenuItem(
                       text = {
                           Text(text = it)
                       },
                       onClick = {
                           ticketInformationViewModel.selectDropdownComponent(index, text = it)
                           isDropdownOpen = false
                       }
                   )
               }
            }
        }
    }
}