package com.jefisu.movist.ui.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.movist.data.dto.Movie

@ExperimentalMaterialApi
@Composable
fun PersonalizedCard(
    movie: Movie
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 6.dp)
            .animateContentSize(
                tween(durationMillis = 200, easing = FastOutLinearInEasing)
            ),
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(15.dp),
        onClick = { isExpanded = !isExpanded }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.weight(0.9f)) {
                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (isExpanded) {
                    Text(
                        text = movie.description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
