package com.softcatcode.vkclient.presentation.home.comments

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softcatcode.vkclient.domain.entities.Comment
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.softcatcode.vkclient.R
import com.softcatcode.vkclient.domain.entities.PostData

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = comment.authorAvatarUrl,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${comment.authorName} (${comment.id})",
                fontSize = 12.sp,
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = comment.content,
                fontSize = 14.sp,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = comment.date,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun CommentList(
    modifier: Modifier = Modifier,
    commentList: List<Comment>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = commentList,
            key = { it.id }
        ) {
            CommentItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 2.dp),
                comment = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.comment_top_bar_title),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun CommentScreen(
    post: PostData,
    onBackPressed: () -> Unit
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(
            post,
            LocalContext.current.applicationContext as Application
        )
    )
    val state = viewModel.state.collectAsState(CommentsScreenState.Initial)
    val currentState = state.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                CommentTopBar(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    onBackPressed = onBackPressed
                )
            }
        ) {
            CommentList(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(it)
                    .padding(start = 5.dp, bottom = 50.dp),
                commentList = currentState.commentList
            )
        }
    }

}