package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.components

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationEvents
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationsViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components.ScheduleItem
import kotlinx.coroutines.delay
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsBodySections(notificationsViewModel: NotificationsViewModel) {
    val activity = LocalContext.current as Activity
    val swipeToDismissState = rememberSwipeToDismissBoxState()



    if (notificationsViewModel.state.isLoading) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            properties = DialogProperties(
                dismissOnBackPress = true
            )
        ) {

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.button_color),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }
    var currentDateCategory: String? = null
    LazyColumn {
        notificationsViewModel.state.data.forEachIndexed { index, notification ->
            val dateCategory = Utils.categorizeDate(notification.createdAt)

            // Display the date category if it's different from the previous one
            item {
                if (currentDateCategory != dateCategory) {
                    Text(
                        text = dateCategory,
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.button_color),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    currentDateCategory = dateCategory
                }
            }

            // Display the ScheduleItem for this work order
            item {
                NotificationItem(
                    index = index,
                    title = notificationsViewModel.state.data[index].title,
                    id = notificationsViewModel.state.data[index].id,
                    description = notificationsViewModel.state.data[index].description,
                    status = notificationsViewModel.state.data[index].status,
                    createdAt = notificationsViewModel.state.data[index].createdAt,
                    onDeleted = {
                        notificationsViewModel.event(
                            NotificationEvents.ClearNotification(
                                activity = activity,
                                notificationId = notificationsViewModel.state.data[index].id
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))

            }
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }


}
