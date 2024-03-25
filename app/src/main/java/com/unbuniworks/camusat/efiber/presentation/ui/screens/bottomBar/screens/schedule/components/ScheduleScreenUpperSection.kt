package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreenUpperSection(
    scheduleScreenViewModel: ScheduleScreenViewModel
) {
    val state = rememberTimePickerState()

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 60.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Schedule",
            color = colorResource(id = R.color.button_color),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Search",
                    color = Color.DarkGray,
                )
            },
            value = scheduleScreenViewModel.formatDate(),
            onValueChange = {
                            scheduleScreenViewModel.updateSearch(searchValue = it)
            },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "search"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    scheduleScreenViewModel.openOrCloseDatePicker()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "search"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
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
            modifier = Modifier.fillMaxWidth(),
        )
    }
    if (scheduleScreenViewModel.isDatePickerOpen){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
        ) {
            val datePickerState =
                rememberDatePickerState(initialSelectedDateMillis = scheduleScreenViewModel.selectedDate)
            DatePickerDialog(
                onDismissRequest = {
                    scheduleScreenViewModel.openOrCloseDatePicker()
                },
                confirmButton = {
                    scheduleScreenViewModel.updateSelectedDate(
                        date = datePickerState.selectedDateMillis ?: 0L
                    )

                }
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = colorResource(id = R.color.light_blue),
                    )
                )
            }


        }
    }
}