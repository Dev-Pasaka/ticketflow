package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.resetPassword

import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ResetPassword(
    navController: NavHostController
) {
   val  resetPasswordViewModel:ResetPasswordViewModel  =  viewModel<ResetPasswordViewModel>()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetX by animateFloatAsState(
        targetValue = if (startAnimation) 0f else with(LocalDensity.current) { -50.dp.toPx() },
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        scope.launch {
            startAnimation = true
            delay(1000)
        }
    }


    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                //Logo
                Image(
                    painter = painterResource(id = R.drawable.ticket_flow_logo),
                    contentDescription = "Camusat logo",
                    modifier = Modifier
                        .width(150.dp)
                        .height(70.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = "Language Icon",
                        tint = Color.DarkGray,

                        )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "English", color = Color.DarkGray)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "Language Icon",
                            tint = Color.DarkGray,

                            )
                    }
                }
            }




            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = offsetX.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ticket_flow_logo_product_removebg_preview),
                    contentDescription = "Logo product",
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.update_password_lable),
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.new_password),
                            color = Color.DarkGray,
                        )
                    },
                    value = resetPasswordViewModel.newPassword,
                    onValueChange = {
                        resetPasswordViewModel.updateNewPassword(text = it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    visualTransformation = if (resetPasswordViewModel.isNewPasswordVisible) VisualTransformation.None else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        when (!resetPasswordViewModel.isNewPasswordVisible) {
                            true -> IconButton(onClick = { resetPasswordViewModel.showOrHideNewPassword() }) {
                                Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "Show password")
                            }

                            false -> {
                                IconButton(onClick = { resetPasswordViewModel.showOrHideNewPassword() }) {
                                    Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "Hide password")
                                }
                            }
                        }
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = colorResource(id = R.color.button_color),
                    ),
                    modifier = Modifier.fillMaxWidth(0.75f),
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.confirm_new_password),
                            color = Color.DarkGray,
                        )
                    },
                    value = resetPasswordViewModel.confirmNewpPassword,
                    onValueChange = {
                        resetPasswordViewModel.updateConfirmNewPassword(text = it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    visualTransformation = if (resetPasswordViewModel.isConfirmNewPasswordVisible) VisualTransformation.None else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        when (!resetPasswordViewModel.isConfirmNewPasswordVisible) {
                            true -> IconButton(onClick = { resetPasswordViewModel.showOrHideConfirmNewPassword() }) {
                                Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "Show password")
                            }

                            false -> {
                                IconButton(onClick = { resetPasswordViewModel.showOrHideConfirmNewPassword() }) {
                                    Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "Hide password")
                                }
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = colorResource(id = R.color.button_color),
                    ),
                    modifier = Modifier.fillMaxWidth(0.75f),
                )

                Spacer(modifier = Modifier.height(8.dp))


                when (resetPasswordViewModel.updatePasswordState.isLoading) {
                    true -> CircularProgressIndicator(
                        color = colorResource(id = R.color.button_color),
                        strokeWidth = 3.dp,
                        strokeCap = StrokeCap.Butt
                    )

                    else -> {
                        Button(
                            enabled = (resetPasswordViewModel.newPassword == resetPasswordViewModel.confirmNewpPassword) &&
                                    (resetPasswordViewModel.newPassword.isNotBlank() && resetPasswordViewModel.confirmNewpPassword.isNotBlank()),
                            onClick = {
                                scope.launch {
                                    resetPasswordViewModel.updatePassword(activity = context as Activity, navController = navController)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.button_color),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier.fillMaxWidth(0.75f),

                            ) {
                            Text(
                                text = stringResource(id = R.string.update),
                            )
                        }
                    }
                }


            }
            Spacer(modifier = Modifier.height(64.dp))

        }

    }

}
