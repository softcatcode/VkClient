package com.softcatcode.vkclient.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.softcatcode.vkclient.R
import com.softcatcode.vkclient.domain.entities.Friend
import com.softcatcode.vkclient.domain.entities.ProfileData
import com.softcatcode.vkclient.presentation.news.IconWithTextAndHint
import com.softcatcode.vkclient.presentation.ui.theme.LocationIconColor
import com.softcatcode.vkclient.presentation.ui.theme.OnlineColor

@Composable
fun ProfileHat(modifier: Modifier = Modifier, profile: ProfileData) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        IconWithTextAndHint(
            model = profile.avatarUrl,
            label = "${profile.name} ${profile.lastName}",
            hint = "${profile.age} ${stringResource(id = R.string.year)}"
        )
    }
}

@Composable
private fun LocationLabel(modifier: Modifier = Modifier, country: String?, city: String?) {
    val locationName = if (country != null && city != null)
        "$country, $city"
    else country ?: city ?: stringResource(id = R.string.location_undefined)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background),
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = null,
            tint = LocationIconColor
        )
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier,
            text = locationName,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun AvatarIcon(imgUrl: String, isOnline: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(0.6f),
        contentAlignment = Alignment.BottomEnd
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background),
            model = imgUrl,
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        if (isOnline) {
            Spacer(
                modifier = Modifier
                    .padding(end = 5.dp, bottom = 5.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(OnlineColor)
            )
        }
    }
}

@Composable
fun FriendCard(modifier: Modifier, friend: Friend) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarIcon(imgUrl = friend.avatarUrl, isOnline = friend.online)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "${friend.name}\n${friend.lastName}",
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SectionTitle(titleId: Int) {
    Text(
        modifier = Modifier.padding(5.dp),
        text = stringResource(titleId),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
}

@Composable
fun FriendList(modifier: Modifier = Modifier, friends: List<Friend>) {
    Column(modifier = modifier) {
        SectionTitle(titleId = R.string.friends)
        LazyRow(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(items = friends, key = { it.id }) {
                FriendCard(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(start = 5.dp, end = 5.dp),
                    friend = it
                )
            }
        }
    }
}

@Composable
fun Photos(modifier: Modifier = Modifier, photoLinks: List<String>) {
    val photoSize = LocalConfiguration.current.screenWidthDp.div(3).dp - 5.dp
    Column(
        modifier = modifier
    ) {
        SectionTitle(titleId = R.string.photos)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(bottom = 50.dp),
            columns = GridCells.FixedSize(photoSize),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items(items = photoLinks) {
                AsyncImage(
                    modifier = Modifier.size(photoSize),
                    model = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
fun ProfileContent(profile: ProfileData) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileHat(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(100.dp),
            profile = profile
        )
        LocationLabel(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.background),
            country = profile.country,
            city = profile.city
        )
        FriendList(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            friends = profile.friends
        )
        Photos(
            modifier = Modifier,
            photoLinks = profile.photoLinks
        )
    }
}