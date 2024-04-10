package com.softcatcode.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNavigationState(controller: NavHostController = rememberNavController()) = remember {
    NavigationState(controller)
}