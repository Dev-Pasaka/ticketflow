package com.unbuniworks.camusat.efiber.ui.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R

@Composable
fun TopAppBar() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.camusat_logo),
            contentDescription ="Camusat logo",
            modifier = Modifier
                .width(150.dp)
                .height(70.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = "person Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_on_icon),
                    contentDescription = "Language Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)

                )
            }
        }
    }

}