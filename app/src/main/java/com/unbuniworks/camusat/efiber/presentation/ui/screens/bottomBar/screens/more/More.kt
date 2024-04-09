package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.BottomNavigationViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.BottomAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.NavDrawerContent
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.components.MoreScreenBodySection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.components.MoreScreenUpperSection
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoreScreen(
    bottomNavigationViewModel: BottomNavigationViewModel,
    moreScreenViewModel: MoreScreenViewModel,
    navController: NavHostController
){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavDrawerContent(navController = navController)
        }
    ) {


        Scaffold(
            bottomBar = {
                BottomAppBar(
                    bottomNavigationViewModel = bottomNavigationViewModel,
                    navController = navController
                )
            },
            topBar = {
                TopAppBar(
                    navController = navController,
                    actionOpenNavDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

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
}