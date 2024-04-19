package com.softcatcode.vkclient.domain.entities

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.softcatcode.vkclient.R
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Parcelize
data class PostData(
    val id: String,
    val communityName: String,
    val publicationDate: String,
    val avatarUrl: String,
    val contentImageUrl: String?,
    val contentText: String,
    val statistics: List<StatisticsItem>,
    val liked: Boolean = Random.nextBoolean()
): Parcelable {
    companion object {

        val NavigationType = object: NavType<PostData>(false) {

            override fun get(bundle: Bundle, key: String): PostData? = bundle.getParcelable(key)

            override fun parseValue(value: String) = Gson().fromJson(value, PostData::class.java)

            override fun put(bundle: Bundle, key: String, value: PostData) = bundle.putParcelable(key, value)
        }
    }
}
