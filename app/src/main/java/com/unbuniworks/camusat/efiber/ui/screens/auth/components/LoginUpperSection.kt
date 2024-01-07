package com.unbuniworks.camusat.efiber.ui.screens.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
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
import com.unbuniworks.camusat.efiber.ui.screens.auth.LoginScreenViewModel

@Composable
fun LoginScreenUpperSection(loginScreenViewModel: LoginScreenViewModel){
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
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.Outlined.Language,
                contentDescription = "Language Icon",
                tint = Color.DarkGray,

            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "English", color = Color.DarkGray)
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Language Icon",
                    tint = Color.DarkGray,

                    )
            }
        }
    }
}