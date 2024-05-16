package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.LanguageManager
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenLowerSection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenMiddleSection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenUpperSection
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginsScreen(
    loginScreenViewModel: LoginScreenViewModel,
    actionNavigateToSelectModule:() -> Unit
){
    val context = LocalContext.current

    var isSnackBarOpen by remember {
        mutableStateOf(true)
    }
    val openInboxLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        // This block will be executed when the activity result is received
        // Here you can handle any post-action logic if needed
    }
    val intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_APP_EMAIL) // Filter by email apps
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

        Scaffold(
      snackbarHost = {
              if (loginScreenViewModel.resetPasswordState.isSuccessful) {
                  isSnackBarOpen = !isSnackBarOpen
                  Snackbar(
                      containerColor = colorResource(id = R.color.button_link_color),
                      contentColor = Color.White,
                      modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                  ) {
                      Row(
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween,
                          modifier = Modifier.fillMaxWidth()
                      ) {
                          Text(text = "New password has been sent to you email.")
                          TextButton(
                              onClick = {
                                  isSnackBarOpen = !isSnackBarOpen
                                  openInboxLauncher.launch(intent)
                              },
                              colors = ButtonDefaults.buttonColors(
                                  contentColor = Color.White,
                                  containerColor = Color.Transparent

                                  )
                          ) {
                              Text(
                                  text = "Open mail",
                                  fontSize = 12.sp,
                                  textDecoration = TextDecoration.Underline
                              )
                          }
                      }
                  }
              } else if (!loginScreenViewModel.resetPasswordState.isSuccessful &&
                  loginScreenViewModel.resetPasswordState.message.isNotBlank()
              ) {
                  Snackbar(
                      containerColor = colorResource(id = R.color.button_link_color),
                      contentColor = Color.White,
                      modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                  ) {
                      Row(
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween,
                          modifier = Modifier.fillMaxWidth()
                      ) {
                          Text(text = loginScreenViewModel.resetPasswordState.message)
                          IconButton(onClick = { isSnackBarOpen = !isSnackBarOpen }) {
                              Icon(imageVector = Icons.Outlined.Close, contentDescription = "Close")
                          }
                      }
                  }
              }
      }
    ) {
        Surface(
            color = colorResource(id = R.color.background),
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LoginScreenUpperSection(loginScreenViewModel = loginScreenViewModel)
                LoginScreenMiddleSection(loginScreenViewModel = loginScreenViewModel, actionNavigateToSelectModule)
                LoginScreenLowerSection()
            }
        }
    }

    if (!loginScreenViewModel.loginState.error.isNullOrBlank()){
        Toast.makeText(context, loginScreenViewModel.loginState.error, Toast.LENGTH_SHORT).show()
    }else if (loginScreenViewModel.loginState.isSuccessful){
        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

    }


}