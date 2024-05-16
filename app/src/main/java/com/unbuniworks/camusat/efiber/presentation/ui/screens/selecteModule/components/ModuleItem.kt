package com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.SelectModuleViewModel

@Composable
fun ModuleItem(
    selectModuleViewModel: SelectModuleViewModel,
    actionNavigateToHome:() -> Unit
){
    val item = selectModuleViewModel.listOfModules.first()
    Surface(
        color = colorResource(id = R.color.light_blue),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = {
            selectModuleViewModel.updateSelectedModule(name = item)
            actionNavigateToHome()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)

        ) {
            Text(
                text = stringResource(id = R.string.dispatch_module),
                fontWeight = if (selectModuleViewModel.selectedModule == item)
                    FontWeight.Bold
                else FontWeight.Normal,
                color =  Color.DarkGray
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

}