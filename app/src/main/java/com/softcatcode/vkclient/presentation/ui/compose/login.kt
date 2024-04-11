package com.softcatcode.vkclient.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softcatcode.vkclient.R

@Preview
@Composable
fun LoginScreen(
    onLogInBtnClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(id = R.drawable.vk_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier
                .height(200.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp),
            onClick = onLogInBtnClicked,
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(
                text = stringResource(id = R.string.login_btn_text),
                fontSize = 20.sp
            )
        }
    }
}