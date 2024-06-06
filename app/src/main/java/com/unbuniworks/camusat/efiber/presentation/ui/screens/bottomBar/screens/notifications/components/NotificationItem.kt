package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.maps.android.compose.Circle
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationItem(
    index: Int,
    title: String,
    id: String,
    description: String,
    status: String,
    createdAt: String,
    onDeleted: () -> Unit,
) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            contentColor = colorResource(id = R.color.button_color),
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 0.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = Utils.formatIsoTime(
                        createdAt
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 0.dp)
            ) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    //textAlign = TextAlign.Justify,
                )
            }


        }


    }
}