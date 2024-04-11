package com.softcatcode.vkclient.data.mapper

import com.softcatcode.vkclient.data.model.GroupDto
import com.softcatcode.vkclient.data.model.NewsFeedResponseDto
import com.softcatcode.vkclient.data.model.PostDto
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    private fun mapPostDtoToEntity(model: PostDto, group: GroupDto?) = with (model) {
        PostData(
            id = id,
            communityName = group?.name ?: "",
            publicationDate = date.toString(),
            avatarUrl = group?.imgUrl ?: "",
            contentImageUrl = attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
            contentText = text,
            statistics = listOf(
                StatisticsItem(StatisticsType.View, views.count),
                StatisticsItem(StatisticsType.Like, likes.count),
                StatisticsItem(StatisticsType.Share, reposts.count),
                StatisticsItem(StatisticsType.Comment, comments.count)
            )
        )
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
