package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.model.ApplicationItem

@Composable
fun  ApplicationItem(applicationItem: ApplicationItem) {
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        colors  = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = colorResource(id = R.color.button_color)
        ),
        modifier = Modifier
            .height(160.dp)
            .width(250.dp)
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp, vertical =  16.dp)

        ) {
            Icon(
                imageVector = applicationItem.icon,
                contentDescription = applicationItem.name,
                tint = Color.DarkGray,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = applicationItem.name,
                color  = colorResource(id = R.color.button_color),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}