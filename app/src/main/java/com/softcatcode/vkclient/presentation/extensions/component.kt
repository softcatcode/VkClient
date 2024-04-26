package com.softcatcode.vkclient.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.softcatcode.vkclient.VkClientApplication

@Composable
fun getApplicationComponent() =
    (LocalContext.current.applicationContext as VkClientApplication).component