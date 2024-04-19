package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItem(index: Int, scheduleScreenViewModel: ScheduleScreenViewModel, navController: NavHostController) {
    val title = scheduleScreenViewModel.getScheduledWorkOrderState.data[index]
    Column {
        Surface(
            onClick = {
                navController.currentBackStackEntry?.arguments?.putString(
                    "workOrderId",
                    title.id
                )
                navController.navigate(route = "ticket_information")
            },
            color = Color.Transparent
        ) {

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    contentColor = Color.DarkGray,
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "${index + 1}. ${title.ticketId}",
                        fontSize = 12.sp,

                        )

                    Text(
                        text = Utils.formatIsoTime(
                            title.dueDate
                        ),
                        fontSize = 12.sp,

                        )
                }
            }
        }
    }
}