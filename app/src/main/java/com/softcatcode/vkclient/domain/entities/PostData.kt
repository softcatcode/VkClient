package com.softcatcode.vkclient.domain.entities

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.softcatcode.vkclient.R
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostData(
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "some weird text",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticsItem> = listOf(
        StatisticsItem(StatisticsType.View, 916),
        StatisticsItem(StatisticsType.Share, 7),
        StatisticsItem(StatisticsType.Comment, 8),
        StatisticsItem(StatisticsType.Like, 23),
    ),
    val id: Int = UNDEFINED_ID
): Parcelable {
    companion object {
        const val UNDEFINED_ID = -1

        val NavigationType = object: NavType<PostData>(false) {

            override fun get(bundle: Bundle, key: String): PostData? = bundle.getParcelable(key)

            override fun parseValue(value: String) = Gson().fromJson(value, PostData::class.java)

            override fun put(bundle: Bundle, key: String, value: PostData) = bundle.putParcelable(key, value)
        }
    }
}
