package com.unbuniworks.camusat.efiber.ui.screens.auth.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.commonComponents.CustomCircularProgressBar
import com.unbuniworks.camusat.efiber.ui.screens.auth.LoginScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreenMiddleSection(
    loginScreenViewModel: LoginScreenViewModel,
    actionNavigateToSelectModule: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.camusat_logo_product_removebg_preview),
            contentDescription = "Logo product",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        )

        when (loginScreenViewModel.isLoginSelected) {
            "Login" -> Login(
                loginScreenViewModel = loginScreenViewModel,
                actionNavigateToSelectModule
            )

            "ForgotPassword" -> ForgotPassword(loginScreenViewModel = loginScreenViewModel)
        }


    }
}

@Composable
fun Login(loginScreenViewModel: LoginScreenViewModel, actionNavigateToSelectModule: () -> Unit) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetX by animateFloatAsState(
        targetValue = if (startAnimation) 0f else with(LocalDensity.current) { -50.dp.toPx() },
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        scope.launch {
            startAnimation = true
            delay(1000)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
    ) {
        Text(
            text = "Login",
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Email",
                    color = Color.DarkGray,
                )
            },
            value = loginScreenViewModel.email,
            onValueChange = {
                loginScreenViewModel.updateEmail(emailString = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
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
            modifier = Modifier.fillMaxWidth(0.75f),
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.DarkGray,
                )
            },
            value = loginScreenViewModel.password,
            onValueChange = {
                loginScreenViewModel.updatePassword(passwordString = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Done, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoardController?.hide()
                    actionNavigateToSelectModule()
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
            modifier = Modifier.fillMaxWidth(0.75f),
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (loginScreenViewModel.loginState.loading) {
            true -> {
                CustomCircularProgressBar(progress = 0.7f, modifier = Modifier.size(25.dp))
            }
            else -> {
                Button(
                    onClick = actionNavigateToSelectModule,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_color),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(0.75f),

                    ) {
                    Text(
                        text = "Login",
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            onClick = { loginScreenViewModel.updateLoginState(newState = "ForgotPassword") },
            color = colorResource(id = R.color.background)
        ) {
            Text(
                text = "Forgot Password?",
                color = colorResource(id = R.color.button_color)
            )
        }
    }

}

@Composable
fun ForgotPassword(loginScreenViewModel: LoginScreenViewModel) {
    val scope = rememberCoroutineScope()

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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
    ) {
        Text(
            text = "Forgot Password",
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Enter Email",
                    color = Color.DarkGray,
                )
            },
            value = loginScreenViewModel.emailReset,
            onValueChange = {
                loginScreenViewModel.updateEmailReset(emailString = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
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
            modifier = Modifier.fillMaxWidth(0.75f),
        )

        Spacer(modifier = Modifier.height(8.dp))



        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(0.75f),

            ) {
            Text(
                text = "Reset",
            )
        }

    }
    Spacer(modifier = Modifier.height(16.dp))

    Surface(
        onClick = { loginScreenViewModel.updateLoginState(newState = "Login") },
        color = colorResource(id = R.color.background)
    ) {
        Text(
            text = "Back to login",
            color = colorResource(id = R.color.button_color)
        )
    }
}
