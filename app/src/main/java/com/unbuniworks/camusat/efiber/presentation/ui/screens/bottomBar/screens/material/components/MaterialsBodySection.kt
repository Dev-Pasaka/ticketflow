package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialsState

@Composable
fun MaterialsBodySection(materialScreenViewModel: MaterialScreenViewModel) {

    LazyColumn{


        items(count = materialScreenViewModel.materialsState?.data?.size ?: 0){
            val material = materialScreenViewModel.materialsState?.data?.get(it)

            ElevatedCard(
                colors = CardDefaults.cardColors(
                    contentColor = Color.DarkGray,
                    containerColor = Color.White
                ),
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "${it+1}     ",
                            fontSize = 14.sp,
                        )

                        Text(
                            text = material?.name ?: "",
                            color = Color.DarkGray,
                            fontSize = 14.sp
                        )
                    }
                    Text(
                        text = material?.qty ?: "",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp,)
                    )
                }

            }
        }
    }
}