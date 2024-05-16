package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents

@Composable

fun MaterialsScreenUpperSection(materialScreenViewModel: MaterialScreenViewModel) {
    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 70.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.materials_lable),
                color = colorResource(id = R.color.button_color),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )


            IconButton(
                onClick = {
                    materialScreenViewModel.refresh()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.light_blue),
                    contentColor = Color.White,
                )
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)

        ) {

            OutlinedTextField(
                readOnly = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.select_project),
                        color = colorResource(id = R.color.button_color),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                },
                value = materialScreenViewModel.selectedMaterial,
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.DarkGray,
                    unfocusedTextColor = Color.DarkGray,
                    cursorColor = Color.DarkGray,
                    focusedIndicatorColor = colorResource(id = R.color.button_color),
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            materialScreenViewModel.openOrCloseMaterialsDropDown()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = colorResource(id = R.color.button_color),

                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Project",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )


        }


        Surface(
            color = colorResource(id = R.color.light_gray),

            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.item_number),
                    fontSize = 14.sp,
                )

                Text(
                    text = stringResource(id = R.string.available_quantity),
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }

        }
    }

    if (materialScreenViewModel.isFilterMaterialsDropDownOpen){
        FilterMaterialsDropDown(
            materialScreenViewModel = materialScreenViewModel,
            isExpanded = true ,
            materialFilter = materialScreenViewModel.filterMaterials,
            actionOnDismiss = {
                materialScreenViewModel.openOrCloseMaterialsDropDown()
            }
        )


    }

}