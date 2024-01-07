package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more

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
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.components.MoreScreenBodySection
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.components.MoreScreenUpperSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoreScreen(
    bottomNavigationViewModel: BottomNavigationViewModel,
    moreScreenViewModel: MoreScreenViewModel,
    navController: NavHostController
){

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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            MoreScreenUpperSection(moreScreenViewModel = moreScreenViewModel)
            MoreScreenBodySection(moreScreenViewModel = moreScreenViewModel)
        }
    }
}