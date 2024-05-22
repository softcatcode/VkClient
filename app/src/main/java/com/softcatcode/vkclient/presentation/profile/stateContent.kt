package com.softcatcode.vkclient.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.presentation.extensions.ProgressBar
import com.softcatcode.vkclient.presentation.extensions.getApplicationComponent

@Composable
fun Profile() {
    val component = getApplicationComponent()
    val viewModel: ProfileViewModel = viewModel( factory = component.getViewModelFactory() )
    val state = viewModel.state.collectAsState(initial = ProfileScreenState.Initial)

    when (val currentState = state.value) {
        is ProfileScreenState.Content -> ProfileContent(currentState.data)

        ProfileScreenState.Initial -> {}

        ProfileScreenState.Loading -> ProgressBar()
    }
}