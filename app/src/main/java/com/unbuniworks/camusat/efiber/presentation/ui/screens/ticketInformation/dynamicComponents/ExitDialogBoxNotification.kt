package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.unbuniworks.camusat.efiber.R

@Composable
fun ExitDialogBoxNotification(
    icon:ImageVector,
    dialogTitle:String,
    dialogText: String,
    onDismissRequest:()-> Unit,
    onConfirmation:()-> Unit


) {
    AlertDialog(
        containerColor = colorResource(id = R.color.background),
        icon = {
            Icon(icon, contentDescription = "AlertDialogIcon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.exit_ticket_template_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.exit_ticket_template_dialog_dismiss))
            }
        }
    )
}