package com.softcatcode.vkclient.data.mapper

import com.softcatcode.vkclient.data.model.GroupDto
import com.softcatcode.vkclient.data.model.LikeCountResponseDto
import com.softcatcode.vkclient.data.model.NewsFeedResponseDto
import com.softcatcode.vkclient.data.model.PostDto
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

class NewsFeedMapper {

    private fun mapPostDtoToEntity(model: PostDto, group: GroupDto?) = with (model) {
        PostData(
            id = id,
            communityId = communityId,
            communityName = group?.name ?: "",
            publicationDate = mapLongToDate(date),
            avatarUrl = group?.imgUrl ?: "",
            contentImageUrl = attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
            contentText = text,
            liked = likes.userLikes == 1,
            statistics = listOf(
                StatisticsItem(StatisticsType.View, views.count),
                StatisticsItem(StatisticsType.Like, likes.count),
                StatisticsItem(StatisticsType.Share, reposts.count),
                StatisticsItem(StatisticsType.Comment, comments.count)
            )
        )
    }

    private fun mapLongToDate(seconds: Long): String {
        val date = Date(seconds * 1000)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<PostData> {
        val result = mutableListOf<PostData>()
        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups
        for (model in posts) {
            val group = groups.find { it.id == model.communityId.absoluteValue }
            val post = mapPostDtoToEntity(model, group)
            result.add(post)
        }
        return result
    }
}
