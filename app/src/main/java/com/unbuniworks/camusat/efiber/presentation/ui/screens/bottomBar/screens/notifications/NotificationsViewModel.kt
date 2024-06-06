package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.usecase.ClearAllNotificationUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.ClearNotificationUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.GetAllNotificationUseCase
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val clearAllNotificationUseCase: ClearAllNotificationUseCase = ClearAllNotificationUseCase(),
    private val getAllNotificationUseCase: GetAllNotificationUseCase = GetAllNotificationUseCase(),
    private val clearNotificationUseCase: ClearNotificationUseCase = ClearNotificationUseCase()
) : ViewModel() {

    var state by mutableStateOf(NotificationState())

    fun event(event: NotificationEvents) {
        when (event) {
            is NotificationEvents.ClearAllNotifications -> {
                viewModelScope.launch {
                    clearAllNotificationUseCase.clearAllNotification(activity = event.activity)
                        .collect { request ->
                            state = when (request) {
                                is Resource.Loading -> {
                                    NotificationState(
                                        isLoading = true, message = request.message ?: "Loading"
                                    )
                                }

                                is Resource.Success -> {
                                    NotificationState(
                                        isLoading = false,
                                        status = true,
                                        data = request.data ?: emptyList(),
                                        message = request.message.toString()
                                    )
                                }

                                is Resource.Error -> {
                                    NotificationState(
                                        isLoading = false,
                                        status = false,
                                        message = request.message.toString()
                                    )

                                }

                            }
                        }
                }
            }

            is NotificationEvents.ClearNotification -> {
                viewModelScope.launch {
                    clearNotificationUseCase.clearNotification(
                        activity = event.activity, notificationId = event.notificationId
                    ).collect { request ->
                        state = when (request) {
                            is Resource.Loading -> {
                                NotificationState(
                                    isLoading = true, message = request.message ?: "Loading"
                                )
                            }

                            is Resource.Success -> {
                                NotificationState(
                                    isLoading = false,
                                    status = true,
                                    data = request.data ?: emptyList(),
                                    message = request.message.toString()
                                )
                            }

                            is Resource.Error -> {
                                NotificationState(
                                    isLoading = false,
                                    status = false,
                                    message = request.message.toString()
                                )

                            }

                        }
                    }
                }
            }

            is NotificationEvents.GetAllNotifications -> {
                viewModelScope.launch {
                    getAllNotificationUseCase.getAllNotification(activity = event.activity)
                        .collect { request ->
                            state = when (request) {
                                is Resource.Loading -> {
                                    NotificationState(
                                        isLoading = true, message = request.message ?: "Loading"
                                    )
                                }

                                is Resource.Success -> {
                                    NotificationState(
                                        isLoading = false,
                                        status = true,
                                        data = request.data ?: emptyList(),
                                        message = request.message.toString()
                                    )
                                }

                                is Resource.Error -> {
                                    NotificationState(
                                        isLoading = false,
                                        status = false,
                                        message = request.message.toString()
                                    )

                                }

                            }
                        }
                }
            }
        }
    }
}