package com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.SelectModuleViewModel

@Composable
fun SelectModuleBodySection(
    selectModuleViewModel: SelectModuleViewModel,
    actionNavigateToHome:() -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        ModuleItem(selectModuleViewModel = selectModuleViewModel,  actionNavigateToHome = actionNavigateToHome )

    }

}