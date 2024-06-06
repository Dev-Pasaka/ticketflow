package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialsState

@Composable
fun MaterialsBodySection(materialScreenViewModel: MaterialScreenViewModel) {
    if (materialScreenViewModel.materialsState.data.isEmpty()){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.icon_new_work_orders),
                contentDescription ="No ticket",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Text(
                text = "No materials available",
                color = colorResource(id = R.color.button_color),
                fontSize = 14.sp
            )

        }
    }else {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            items(
                count = materialScreenViewModel.materialsState.data.size
            ) {
                val material = materialScreenViewModel.materialsState.data[it]


                ElevatedCard(
                    colors = CardDefaults.cardColors(
                        contentColor = Color.DarkGray,
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 5.dp)
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
                                text = "${it + 1}     ",
                                fontSize = 14.sp,
                            )

                            Text(
                                text = material.name,
                                color = Color.DarkGray,
                                fontSize = 14.sp
                            )
                        }
                        Text(
                            text = material.qty,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp,)
                        )
                    }

                }
               if (it >= materialScreenViewModel.materialsState.data.size-1){
                   Spacer(modifier = Modifier.height(32.dp))
               }
            }
        }
    }
}