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
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomDropDown(
    index: Int,
    navController: NavHostController,
    ticketInformationViewModel: TicketInformationViewModel,
) {


    var selectedItem:String? by remember {
        mutableStateOf(null)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)

    ) {

        OutlinedTextField(
            readOnly = true,
            placeholder = {
                Text(
                    text = "",
                    color = Color.DarkGray,
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        ticketInformationViewModel.openOrCloseDropDown(index)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop down",
                        tint = Color.DarkGray,
                    )
                }
            },
            value = selectedItem ?: "",
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

        DropdownMenu(
            expanded = (ticketInformationViewModel.openOrCloseDropDown.first == index && ticketInformationViewModel.openOrCloseDropDown.second),
            onDismissRequest = {
                ticketInformationViewModel.openOrCloseDropDown(index)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)

        ) {

                    DropdownMenuItem(
                        text = {
                            Text(text = "")
                        },
                        onClick = {

                        }
                    )
                }
    }
}