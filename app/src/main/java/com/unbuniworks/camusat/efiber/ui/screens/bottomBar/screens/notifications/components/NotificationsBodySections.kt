package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.NotificationsViewModel

@Composable
fun NotificationsBodySections(notificationsViewModel: NotificationsViewModel) {
    LazyColumn {
        items(notificationsViewModel.notifications.size) {
            Spacer(modifier = Modifier.height(5.dp))
            if (it == 0){
                Text(
                    text = "Today",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.button_link_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }else if(it == 2){
                Text(
                    text = "Yesterday",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.button_link_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            NotificationItem(index = it, notificationsViewModel = notificationsViewModel)
        }
    }
}