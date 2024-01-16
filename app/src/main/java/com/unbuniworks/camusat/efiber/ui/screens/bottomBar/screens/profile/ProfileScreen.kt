package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.profile.components.ProfileScreenBodySection
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.profile.components.ProfileScreenUpperSection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, profileScreenViewModel: ProfileScreenViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(navController = navController)
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.background)
    )
    {
        Column {
            Spacer(modifier = Modifier.height(48.dp))
            ProfileScreenUpperSection(navController = navController)
            ProfileScreenBodySection(profileScreenViewModel = profileScreenViewModel)
            Button(
                enabled = false,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_blue),
                    contentColor  = Color.DarkGray
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onClick = { /*TODO*/ }
            ) {

                Text(
                    text = "Safety Passport",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}