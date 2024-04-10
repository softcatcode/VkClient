package com.softcatcode.vkclient.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import com.softcatcode.vkclient.presentation.ui.compose.VkMainScreen
import com.softcatcode.vkclient.presentation.ui.theme.VkClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(
                contract = VK.getVKAuthActivityResultContract()
            ) {
                when (it) {
                    is VKAuthenticationResult.Failed -> {
                        Log.i("mumu", "user was logined")
                    }
                    is VKAuthenticationResult.Success -> {
                        Log.i("mumu", "user failed to login")
                    }
                }
            }.apply {
                launch(listOf(VKScope.WALL))
            }
            VkClientTheme {
                VkMainScreen()
            }
        }
    }
}