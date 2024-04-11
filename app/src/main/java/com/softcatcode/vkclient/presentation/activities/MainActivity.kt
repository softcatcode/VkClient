package com.softcatcode.vkclient.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.states.AuthState
import com.softcatcode.vkclient.presentation.ui.compose.LoginScreen
import com.softcatcode.vkclient.presentation.ui.compose.VkMainScreen
import com.softcatcode.vkclient.presentation.ui.theme.VkClientTheme
import com.softcatcode.vkclient.presentation.viewModels.AuthViewModel
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