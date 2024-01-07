package com.unbuniworks.camusat.efiber.ui.screens.selecteModule.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R

@Composable
fun SelectModuleUpperSection(){
   Column {
       Row(
           horizontalArrangement = Arrangement.Start,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp)
       ) {

           Image(
               painter = painterResource(id = R.drawable.camusat_logo),
               contentDescription ="Camusat logo",
               modifier = Modifier
                   .width(150.dp)
                   .height(70.dp)
           )

       }

       Row(
           horizontalArrangement = Arrangement.Start,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 16.dp)
       ) {

           Text(
               text = "Select Module",
               color = colorResource(id = R.color.button_color),
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold
               )

       }
   }
}