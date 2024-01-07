package com.unbuniworks.camusat.efiber.ui.screens.selecteModule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components.SelectModuleBodySection
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components.SelectModuleUpperSection

@Composable
fun SelectModule(selectModuleViewModel: SelectModuleViewModel, actionNavigateToHome:()->Unit){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background),

    ) {
        Column {
            SelectModuleUpperSection()
            SelectModuleBodySection(selectModuleViewModel = selectModuleViewModel, actionNavigateToHome = actionNavigateToHome)
        }

    }
}