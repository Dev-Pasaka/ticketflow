package com.unbuniworks.camusat.efiber.ui.commonComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SignalCellularAlt
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.navigation.BottomNavigationViewModel

@Composable
fun BottomAppBar(bottomNavigationViewModel: BottomNavigationViewModel, navController:NavHostController) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                bottomNavigationViewModel.bottomBarDestinations.forEachIndexed {it, _ ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = true),
                                enabled = bottomNavigationViewModel.selectedDestination.title != bottomNavigationViewModel.bottomBarDestinations[it].title
                            ) {
                                bottomNavigationViewModel.updateSelectedDestination(
                                    bottomBarDestinations = bottomNavigationViewModel.bottomBarDestinations[it]
                                )
                                navController.navigate(bottomNavigationViewModel.selectedDestination.route) {
                                    navController.popBackStack()
                                }
                            }


                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                        ) {

                            if (bottomNavigationViewModel.bottomBarDestinations[it].title == "Request") {
                                Icon(
                                    imageVector = Icons.Outlined.SignalCellularAlt,
                                    contentDescription = bottomNavigationViewModel.bottomBarDestinations[it].title,
                                    tint = colorResource(id = R.color.button_color),
                                    modifier = Modifier.size(30.dp)
                                )

                            } else {
                                Icon(
                                    painter = painterResource(id = bottomNavigationViewModel.bottomBarDestinations[it].icon),
                                    contentDescription = bottomNavigationViewModel.bottomBarDestinations[it].title,
                                    tint = colorResource(id = R.color.button_color),
                                    modifier = if (
                                        bottomNavigationViewModel.selectedDestination.title
                                        == bottomNavigationViewModel.bottomBarDestinations[it].title
                                    ) Modifier.size(32.dp) else Modifier.size(30.dp)

                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = bottomNavigationViewModel.bottomBarDestinations[it].title,
                                color = colorResource(id = R.color.button_color),
                                fontSize = 12.sp,
                                fontWeight = if (
                                    bottomNavigationViewModel.selectedDestination.title
                                    == bottomNavigationViewModel.bottomBarDestinations[it].title
                                ) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier

                            )
                        }


                    }
                }
            }
        }

    }
}