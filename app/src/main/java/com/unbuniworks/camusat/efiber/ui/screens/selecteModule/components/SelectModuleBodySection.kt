package com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.SelectModuleViewModel

@Composable
fun SelectModuleBodySection(
    selectModuleViewModel: SelectModuleViewModel,
    actionNavigateToHome:() -> Unit
){

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        items(count = selectModuleViewModel.listOfModules.size){
            ModuleItem(index = it, selectModuleViewModel = selectModuleViewModel,  actionNavigateToHome = actionNavigateToHome )
        }
    }
}