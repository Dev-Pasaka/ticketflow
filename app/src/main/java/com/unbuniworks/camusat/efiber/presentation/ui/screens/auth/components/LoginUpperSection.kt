package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.LanguageManager
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.LoginScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreenUpperSection(loginScreenViewModel: LoginScreenViewModel) {
    var isLanguageDropdownOpen by remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            loginScreenViewModel.getLanguage(activity = activity)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.camusat_logo),
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
            Text(
                text = if (loginScreenViewModel.selectedLanguage == "fr") "French" else "English",
                color = Color.DarkGray
            )
            IconButton(
                onClick = {
                    loginScreenViewModel.openOrCloseLanguageDropDown()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Language Icon",
                    tint = Color.DarkGray,

                    )
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (loginScreenViewModel.isLanguageDropDownOpen) {
            ChangeLanguage(
                loginScreenViewModel = loginScreenViewModel
            ) {
                loginScreenViewModel.openOrCloseLanguageDropDown()
            }
        }
    }
}

@Composable
fun ChangeLanguage(
    loginScreenViewModel: LoginScreenViewModel,
    actionOnDismiss: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    DropdownMenu(
        expanded = true,
        onDismissRequest = actionOnDismiss,
        properties = PopupProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        ),
        offset = DpOffset(x = 250.dp, y = (-300).dp),
        modifier = Modifier
            .background(color = Color.White)
            .width(150.dp)
    ) {

        DropdownMenuItem(
            text = {
                Text(text = "English")
            },
            onClick = {
                scope.launch {
                    loginScreenViewModel.changeLanguage(
                        newLanguage = "en",
                        activity = activity
                    )

                    LanguageManager.setLocale(activity, loginScreenViewModel.selectedLanguage)
                    actionOnDismiss()
                }
            }
        )
        DropdownMenuItem(
            text = {
                Text(text = "French")
            },
            onClick = {
                scope.launch {
                    loginScreenViewModel.changeLanguage(
                        newLanguage = "fr",
                        activity = activity
                    )
                    LanguageManager.setLocale(activity, loginScreenViewModel.selectedLanguage)
                    actionOnDismiss()
                }
            }
        )
    }


}

