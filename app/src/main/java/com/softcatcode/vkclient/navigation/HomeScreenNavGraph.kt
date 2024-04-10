package com.softcatcode.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.softcatcode.vkclient.domain.entities.PostData
import com.google.gson.Gson

fun NavGraphBuilder.homeScreenNavGraph(
    newsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (PostData) -> Unit
) {
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.News.route
    ) {
        composable(Screen.News.route) {
            newsScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_POST_JSON) {
                    type = PostData.NavigationType
                }
            )
        ) {
            val post = it.arguments?.getParcelable<PostData>(Screen.KEY_POST_JSON)
                ?: throw RuntimeException("Navigation arguments is null")
            commentsScreenContent(post)
        }
    }
}