package com.softcatcode.vkclient.domain.entities

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Immutable
@Parcelize
data class PostData(
    val id: Long,
    val communityId: Long,
    val communityName: String,
    val publicationDate: String,
    val avatarUrl: String,
    val contentImageUrl: String?,
    val contentText: String,
    val statistics: List<StatisticsItem>,
    val liked: Boolean
): Parcelable {
    companion object {

        val NavigationType = object: NavType<PostData>(false) {

            override fun get(bundle: Bundle, key: String): PostData? = bundle.getParcelable(key)

            override fun parseValue(value: String) = Gson().fromJson(value, PostData::class.java)

            override fun put(bundle: Bundle, key: String, value: PostData) = bundle.putParcelable(key, value)
        }
    }
}
