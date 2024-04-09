package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel

@Composable
fun ScheduleItemBody(scheduleScreenViewModel: ScheduleScreenViewModel) {
    if (scheduleScreenViewModel.getScheduledWorkOrderState.isLoading){
        Dialog(
            onDismissRequest = { /*TODO*/ }
        ) {

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.button_color),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }

    LazyColumn{
        items(scheduleScreenViewModel.getScheduledWorkOrderState.data.size){
            ScheduleItem(index = it, scheduleScreenViewModel = scheduleScreenViewModel)
        }
    }
}