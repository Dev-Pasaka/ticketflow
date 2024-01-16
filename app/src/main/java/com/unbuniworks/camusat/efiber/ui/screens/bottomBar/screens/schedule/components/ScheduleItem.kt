package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel

@Composable
fun ScheduleItem(index:Int, scheduleScreenViewModel: ScheduleScreenViewModel){
    val title = scheduleScreenViewModel.scheduledItems[index]
    ElevatedCard(
        colors = CardDefaults.cardColors(
            contentColor = Color.DarkGray,
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "${index+1}. ${title.scheduleNumber} - ${title.title}",
                fontSize = 14.sp,

                )
        }

    }
}