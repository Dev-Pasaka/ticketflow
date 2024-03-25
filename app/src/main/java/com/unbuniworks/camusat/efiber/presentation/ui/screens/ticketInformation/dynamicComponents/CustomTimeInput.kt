package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TimePicker
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomTimeInput(
    index:Int,
    ticketInformationViewModel: TicketInformationViewModel,
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

        OutlinedTextField(
            readOnly = true,
            placeholder = {
                Text(
                    text = "12.00 am",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                )
            },
            value = "12.00 am",
            textStyle = TextStyle(
                fontSize = 12.sp
            ),
            onValueChange = {
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        selectedIndex = 0
                        ticketInformationViewModel.openOrCloseTimePicker()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock_outline),
                        contentDescription ="Select Date" ,
                        tint = colorResource(id = R.color.button_color)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
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
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp)
                .height(50.dp)

        )

}