package com.softcatcode.vkclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.presentation.home.auth.AuthState
import com.softcatcode.vkclient.presentation.home.auth.AuthViewModel
import com.softcatcode.vkclient.presentation.home.auth.LoginScreen
import com.softcatcode.vkclient.presentation.main.VkMainScreen
import com.softcatcode.vkclient.presentation.ui.theme.VkClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
                val viewModel: AuthViewModel = viewModel()
                val authState = viewModel.state.observeAsState(AuthState.Initial)
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract(),
                    onResult = { viewModel.performAuthResult(it) }
                )
                when (authState.value) {
                    is AuthState.Authorized -> VkMainScreen()
                    is AuthState.NotAuthorized -> LoginScreen {
                        launcher.launch(listOf(VKScope.WALL))
                    }
                    is AuthState.Initial -> {}
                }
            }
        }
    }
}