package com.github.astat1cc.foodtestapp.feed_screen.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.github.astat1cc.foodtestapp.feed_screen.ui.models.FoodUi
import com.github.astat1cc.foodtestapp.ui.theme.Pink
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FoodView(food: FoodUi, onClick: (FoodUi) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .size(135.dp)
                .clip(RoundedCornerShape(16.dp)),
            imageModel = { food.image },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit)
        )
        Column(
            modifier = Modifier
                .height(135.dp)
                .padding(start = 20.dp)
        ) {
            Text(
                text = food.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = food.name,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .border(width = 1.dp, color = Pink, shape = RoundedCornerShape(6.dp))
                        .clickable { onClick(food) }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        text = "4 $",
                        color = Pink
                    )
                }
            }
        }
    }
}