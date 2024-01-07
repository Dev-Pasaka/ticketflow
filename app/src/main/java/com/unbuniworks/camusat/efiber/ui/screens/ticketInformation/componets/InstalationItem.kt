package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material.icons.outlined.Router
import androidx.compose.material.icons.outlined.SatelliteAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun InstallationItem(ticketInformationViewModel: TicketInformationViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "MAC ID:",
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.macId,
            onValueChange = {
                            ticketInformationViewModel.updateMacId(id = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Serial No:",
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.serialNo,
            onValueChange = {
                ticketInformationViewModel.updateSerialNo(serial = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "GPON ID:",
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.gponId,
            onValueChange = {
                ticketInformationViewModel.updateGponId(serial = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
            onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Picture: Router",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector = Icons.Outlined.Router,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_gray),
                    contentColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Picture: Router Back with ID",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector =  Icons.Outlined.CameraAlt,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_gray),
                    contentColor = Color.DarkGray

                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Image: Signal 1310 dBm",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector =  Icons.Outlined.Image,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_gray),
                    contentColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Image: Signal 1490 dBm",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector =  Icons.Outlined.Image,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_gray),
                    contentColor = Color.DarkGray

                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Image: Customer ID",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector =  Icons.Outlined.Image,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors  = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_gray),
                    contentColor = Color.DarkGray

                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(60.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Upload Picture: Client form",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector =  Icons.Outlined.Image,
                contentDescription ="Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Surface(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Text(
                        text = "Scan QR: FAT Junction",
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        imageVector =  Icons.Outlined.QrCode2,
                        contentDescription ="Image",
                        modifier = Modifier
                            .width(100.dp)
                            .height(60.dp)
                    )
                }
            }


        }


        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Surface(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Text(
                        text = "GPS Co-ordinates: Client site",
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        imageVector =  Icons.Outlined.SatelliteAlt,
                        contentDescription ="Image",
                        modifier = Modifier
                            .width(100.dp)
                            .height(60.dp)
                    )
                }
            }


        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Comments",
            fontSize = 16.sp,
            color = colorResource(id = R.color.button_color)
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Enter comments",
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.comments,
            onValueChange = {
                ticketInformationViewModel.updateComments(comment = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.button_color)
            ),
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                text = "Submit Installation",
            )
        }



    }
}