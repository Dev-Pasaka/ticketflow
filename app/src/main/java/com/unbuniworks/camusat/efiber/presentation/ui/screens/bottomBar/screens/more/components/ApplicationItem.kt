package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.model.ApplicationItem

@Composable
fun  ApplicationItem(applicationItem: ApplicationItem) {
    val context = LocalContext.current
    val url = "https://api.ip2location.io/?key=207CBCB1851F5B8E9DFED8804FE75730&ip=41.139.174.91#"

    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        colors  = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = colorResource(id = R.color.button_color)
        ),
        modifier = Modifier
            .height(160.dp)
            .width(250.dp)
            .padding(8.dp)
    ) {
        Surface(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            color = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)

            ) {
                Icon(
                    painter = painterResource(id = applicationItem.icon),
                    contentDescription = applicationItem.name,
                    tint = Color.DarkGray,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.user_manual),
                    color = colorResource(id = R.color.button_color),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}