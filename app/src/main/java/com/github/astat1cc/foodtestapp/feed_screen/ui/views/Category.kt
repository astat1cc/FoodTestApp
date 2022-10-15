package com.github.astat1cc.foodtestapp.feed_screen.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import com.github.astat1cc.foodtestapp.ui.theme.Pink
import com.github.astat1cc.foodtestapp.ui.theme.PinkSemitransparent

@Composable
fun Category(
    isChosen: Boolean,
    category: FoodCategory,
    onClick: (FoodCategory) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .shadow(elevation = if (!isChosen) 16.dp else 0.dp)
            .clip(RoundedCornerShape(6.dp))
    ) {
        Box(
            modifier = Modifier
                .background(if (isChosen) PinkSemitransparent else Color.White)
                .clickable { onClick(category) }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                text = category.name,
                color = if (isChosen) Pink else Color.Black
            )
        }
    }
}