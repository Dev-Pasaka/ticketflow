package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.unbuniworks.camusat.efiber.domain.model.Project
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel

@Composable
fun FilterMaterialsDropDown(
    materialScreenViewModel: MaterialScreenViewModel,
    isExpanded:Boolean,
    materialFilter:List<Project>,
    actionOnDismiss:() -> Unit,
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = actionOnDismiss,
            properties = PopupProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            ),
            offset = DpOffset(x = 250.dp, y = (300).dp),
            modifier = Modifier
                .background(color = Color.White)
                .width(150.dp)
        ) {
            materialFilter.forEach { filter ->
                DropdownMenuItem(
                    text = {
                        Text(text = filter.name)
                    },
                    onClick = {
                        materialScreenViewModel.selectMaterial(name = filter.name, id = filter.id)
                        materialScreenViewModel.openOrCloseMaterialsDropDown()
                    }
                )
            }

    }
}