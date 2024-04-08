package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.camerea

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun PhotoBottomSheetContent(
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController,
    index:Int,
    imageIndex:Int,
    bitmaps :List<Bitmap>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    if (bitmaps.isEmpty()){
        Box (
            modifier = modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
           Text(text = "There are no photos yet")
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier

    ){
        items(bitmaps){bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navController.popBackStack()
                    }
            )

        }
    }
}