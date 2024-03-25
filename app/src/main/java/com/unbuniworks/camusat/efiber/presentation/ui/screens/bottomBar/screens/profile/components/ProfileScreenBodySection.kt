package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.ProfileScreenViewModel
import androidx.compose.ui.unit.dp


@Composable
fun ProfileScreenBodySection(profileScreenViewModel: ProfileScreenViewModel) {
    LazyColumn(
      //  modifier = Modifier.height(300.dp)
    ){
        items(profileScreenViewModel.profileItems.size){
            ProfileItems(index = it, profileScreenViewModel = profileScreenViewModel)
        }
    }
}