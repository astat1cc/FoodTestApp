package com.github.astat1cc.foodtestapp.feed_screen.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Banner(@DrawableRes imageResourceId: Int) {
    Image(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 20.dp)
            .clip(RoundedCornerShape(size = 10.dp)),
        painter = painterResource(id = imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}