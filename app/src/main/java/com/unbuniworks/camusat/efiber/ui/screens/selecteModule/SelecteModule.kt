package com.unbuniworks.camusat.efiber.ui.screens.selecteModule

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components.SelectModuleBodySection
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components.SelectModuleUpperSection
import kotlinx.coroutines.delay

@Composable
fun SelectModule(selectModuleViewModel: SelectModuleViewModel, actionNavigateToHome:()->Unit){
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)

    }
    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnim.value)
        ,


        ) {
        Column {
            SelectModuleUpperSection()
            SelectModuleBodySection(selectModuleViewModel = selectModuleViewModel, actionNavigateToHome = actionNavigateToHome)
        }

    }
}