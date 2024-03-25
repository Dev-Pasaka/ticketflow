package com.unbuniworks.camusat.efiber.presentation.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R



@RequiresApi(Build.VERSION_CODES.Q)
private  val ProductSans = FontFamily(
   // Font(R.font.poppins_light, FontWeight.W300),
    // Font(R.font.poppins_lightitalic, FontWeight.W300),
    // Font(R.font.poppins_blackitalic, FontWeight.W400),
    // Font(R.font.poppins_bolditalic, FontWeight.W400),
    Font(R.font.product_sans_light, FontWeight.W400)
    )
// Set of Material typography styles to start with
@RequiresApi(Build.VERSION_CODES.Q)
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp

    )
)
