package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.navigation.BottomNavigationViewModel
import com.unbuniworks.camusat.efiber.ui.commonComponents.BottomAppBar
import com.unbuniworks.camusat.efiber.ui.commonComponents.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(bottomNavigationViewModel: BottomNavigationViewModel, navController:NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                bottomNavigationViewModel = bottomNavigationViewModel,
                navController = navController
            )
        },
        topBar = {
            TopAppBar()

        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.background)

        ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Home", color = colorResource(id = R.color.button_color))



        }

    }
}