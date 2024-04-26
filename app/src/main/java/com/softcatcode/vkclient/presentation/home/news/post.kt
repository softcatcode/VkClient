package com.softcatcode.vkclient.presentation.home.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.softcatcode.vkclient.R
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.presentation.ui.theme.DarkBlue
import com.softcatcode.vkclient.presentation.ui.theme.DarkRed

@Composable
private fun VkProfileCard(modifier: Modifier = Modifier, post: PostData, cornerRadius: Dp = 0.dp) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            modifier = Modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background),
                model = post.avatarUrl,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier,
                    text = post.communityName,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier,
                    text = post.publicationDate,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp
                )
            }
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = "",
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun StatisticsItemView(
    item: StatisticsItem,
    imageResId: Int,
    onClick: ((StatisticsType) -> Unit)? = null,
    tint: Color = MaterialTheme.colorScheme.onSecondary
) {
    val modifier = if (onClick == null) Modifier else Modifier.clickable { onClick(item.type) }
    Icon(
        modifier = modifier
            .fillMaxHeight()
            .padding(5.dp)
            .size(20.dp),
        painter = painterResource(id = imageResId),
        contentDescription = "",
        tint = tint
    )
    Spacer(modifier = Modifier.width(5.dp))
    Text(
        text = formatCount(item.count),
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
private fun PostStatistics(
    modifier: Modifier = Modifier,
    post: PostData,
    statistics: List<StatisticsItem>,
    onStatisticsItemClickListener: (PostData, StatisticsItem) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val rowModifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(end = 15.dp)
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            statistics.find { it.type == StatisticsType.View }?.let { item ->
                StatisticsItemView(item, R.drawable.ic_views_count)
            }
        }
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            statistics.find { it.type == StatisticsType.Like }?.let { item ->
                StatisticsItemView(
                    item = item,
                    imageResId = if (post.liked) R.drawable.ic_like_set else R.drawable.ic_like,
                    onClick = { onStatisticsItemClickListener(post, item) },
                    tint = if (post.liked) DarkRed else MaterialTheme.colorScheme.onSecondary
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            statistics.find { it.type == StatisticsType.Share }?.let { item ->
                StatisticsItemView(
                    item = item,
                    imageResId = R.drawable.ic_share,
                    onClick = { onStatisticsItemClickListener(post, item) }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            statistics.find { it.type == StatisticsType.Comment }?.let { item ->
                StatisticsItemView(
                    item = item,
                    imageResId = R.drawable.ic_comment,
                    onClick = { onStatisticsItemClickListener(post, item) }
                )
            }
        }
    }
}

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: PostData,
    onStatisticsItemClickListener: (PostData, StatisticsItem) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {
        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            VkProfileCard(
                post = post,
                cornerRadius = 8.dp
            )
            Text(
                text = post.contentText,
                fontSize = 16.sp,
                modifier = Modifier.padding(2.dp)
            )
            AsyncImage(
                model = post.contentImageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 2.dp, end = 2.dp),
                contentScale = ContentScale.FillWidth
            )
            PostStatistics(
                modifier = Modifier.height(30.dp),
                statistics = post.statistics,
                post = post,
                onStatisticsItemClickListener = onStatisticsItemClickListener
            )
        }
    }
}

@Composable
fun PostBackground() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(Color.Red.copy(alpha = 0.5f))
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = stringResource(R.string.delete),
            color = Color.White,
            fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostScreen(
    viewModel: NewsViewModel,
    posts: List<PostData>,
    paddingValues: PaddingValues,
    nextDataLoading: Boolean,
    onStatisticsItemClickListener: (PostData, StatisticsItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) {post ->
            val swipeToDismissState = rememberSwipeToDismissBoxState(
                positionalThreshold = { 150f },
            )
            if (swipeToDismissState.currentValue.name != SwipeToDismissBoxValue.Settled.name)
                viewModel.removePost(post.id)
            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = swipeToDismissState,
                backgroundContent = { PostBackground() }
            ) {
                PostCard(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    post = post,
                    onStatisticsItemClickListener = onStatisticsItemClickListener
                )
            }
        }
        item {
            if (nextDataLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = DarkBlue)
                }
            } else {
                SideEffect {
                    viewModel.loadNextRecommendations()
                }
            }
        }
    }
}

private fun formatCount(count: Int): String{
    return if (count > 100_000)
        String.format("%sK", (count / 1000))
    else if (count > 1000)
        String.format("%.1fK", (count / 1000f))
    else
        count.toString()
}