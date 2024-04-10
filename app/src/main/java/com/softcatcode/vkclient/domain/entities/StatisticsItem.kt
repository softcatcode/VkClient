package com.softcatcode.vkclient.domain.entities

import android.os.Parcelable
import com.softcatcode.vkclient.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatisticsItem(
    val type: StatisticsType,
    val count: Int
): Parcelable {
    companion object {
        fun matchDrawableId(type: StatisticsType) = when (type) {
            StatisticsType.Like -> R.drawable.ic_like
            StatisticsType.Comment -> R.drawable.ic_comment
            StatisticsType.Share -> R.drawable.ic_share
            StatisticsType.View -> R.drawable.ic_views_count
        }
    }
}
