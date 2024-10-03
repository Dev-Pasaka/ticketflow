package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components

import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.LoginScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

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
            painter = painterResource(id = R.drawable.ticket_flow_logo_product_removebg_preview),
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

    val context = LocalContext.current
    var locale by rememberSaveable { mutableStateOf(Locale.getDefault().language) }
 /*   locale = if (locale == "en") "fr" else "en"
    LanguageManager.setLocale(context, locale)
    Log.e("Language", locale)*/


    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var email:String? by remember{
        mutableStateOf(null)
    }
    var activity  = LocalContext.current as Activity
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
    LaunchedEffect(key1 = Unit ){
        email = loginScreenViewModel.sharedPreferenceRepository.getString(
            key = Constants.email,
            activity =activity
        )
        loginScreenViewModel.updateEmail(emailString =  email ?: "")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login),
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.user_email),
                    color = Color.DarkGray,
                )
            },
            value = loginScreenViewModel.email,
            onValueChange = {
                loginScreenViewModel.updateEmail(emailString =  it)
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
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.user_password),
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

            visualTransformation = if (isPasswordVisible) VisualTransformation.None else
                PasswordVisualTransformation(),
            trailingIcon = {
                when (isPasswordVisible) {
                    true -> IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "Show password")
                    }

                    else -> {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
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

        Spacer(modifier = Modifier.height(16.dp))

        when (loginScreenViewModel.loginState.isLoading) {
            true -> {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.button_color),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt
                )
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
                        text = stringResource(id = R.string.login),
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
                text = stringResource(id = R.string.forgot_password),
                color = colorResource(id = R.color.button_color)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))





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
            text = stringResource(id = R.string.forgot_password_lable),
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            singleLine = true,
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



        when(loginScreenViewModel.resetPasswordState.isLoading){
            true -> CircularProgressIndicator(
                color = colorResource(id = R.color.button_color),
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Butt
            )
            else ->{
                Button(
                    enabled = loginScreenViewModel.emailReset.isNotBlank(),
                    onClick = {
                        loginScreenViewModel.resetPassword()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.button_color),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(0.75f),

                    ) {
                    Text(
                        text = stringResource(id = R.string.reset),
                    )
                }
            }
        }


    }
    Spacer(modifier = Modifier.height(16.dp))

    Surface(
        onClick = { loginScreenViewModel.updateLoginState(newState = "Login") },
        color = colorResource(id = R.color.background)
    ) {
        Text(
            text = stringResource(id = R.string.back_to_login),
            color = colorResource(id = R.color.button_color)
        )
    }
}

