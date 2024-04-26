package com.softcatcode.vkclient.presentation.navigation

import android.net.Uri
import com.softcatcode.vkclient.domain.entities.PostData
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {
    data object Home: Screen(ROUTE_HOME)
    data object News: Screen(ROUTE_NEWS_FEED)
    data object Favourite: Screen(ROUTE_FAVOURITE)
    data object Profile: Screen(ROUTE_PROFILE)

    data object Comments: Screen(ROUTE_COMMENTS) {

        const val ROUTE_FOR_ARGS = "comments"

        fun getPathWithArg(post: PostData): String {
            val postJson = Gson().toJson(post)
            val encodedJson = Uri.encode(postJson)
            return "$ROUTE_FOR_ARGS/${encodedJson}"
        }
    }

    companion object {
        const val KEY_POST_JSON = "post_json"

        const val ROUTE_NEWS_FEED = "news"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "${Comments.ROUTE_FOR_ARGS}/{$KEY_POST_JSON}"
    }
}