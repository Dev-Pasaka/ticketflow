package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.ProfileScreenViewModel

@Composable
fun ProfileItems(index:Int, profileScreenViewModel: ProfileScreenViewModel) {
    val profile = profileScreenViewModel.profileItems[index]
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = profile.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.button_link_color)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = profile.body,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}