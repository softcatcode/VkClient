package com.softcatcode.vkclient.domain.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.softcatcode.vkclient.R
import com.softcatcode.vkclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val icon: ImageVector,
    val titleResourceId: Int,
) {
    data object Home: NavigationItem(
        screen = Screen.Home,
        icon = Icons.Outlined.Home,
        titleResourceId = R.string.home_title
    )

    data object Favourites: NavigationItem(
        screen = Screen.Favourite,
        icon = Icons.Outlined.Favorite,
        titleResourceId = R.string.favourites_title
    )

    data object Profile: NavigationItem(
        screen = Screen.Profile,
        icon = Icons.Outlined.Person,
        titleResourceId = R.string.profile_title
    )
}