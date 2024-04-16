package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItemBody(scheduleScreenViewModel: ScheduleScreenViewModel, navController:NavHostController) {
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
    if (scheduleScreenViewModel.getScheduledWorkOrderState.data.isEmpty()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.no_schedule),
                contentDescription ="No ticket",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = "You have no scheduled tickets",
                color = colorResource(id = R.color.button_color),
                fontSize = 14.sp
            )

        }
    }else {

        LazyColumn {
            items(scheduleScreenViewModel.getScheduledWorkOrderState.data.size) {
                ScheduleItem(
                    index = it,
                    scheduleScreenViewModel = scheduleScreenViewModel,
                    navController = navController
                )
            }
        }
    }
}