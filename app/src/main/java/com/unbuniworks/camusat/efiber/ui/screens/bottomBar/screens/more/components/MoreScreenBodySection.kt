package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.MoreScreenViewModel

@Composable
fun MoreScreenBodySection(moreScreenViewModel: MoreScreenViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical =  16.dp)
    ) {
        items(moreScreenViewModel.listOfApplicationItems.size) { index ->
            ApplicationItem(applicationItem = moreScreenViewModel.listOfApplicationItems[index])
        }
    }
}