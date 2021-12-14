package com.jefisu.movist.features.presentation.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.movist.features.domain.model.Movie

@ExperimentalMaterialApi
@Composable
fun DefaultCard(
    movie: Movie,
    onDeleteClick: () -> Unit = {}
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 6.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutLinearInEasing
                )
            ),
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete icon",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onDeleteClick() }
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        modifier = Modifier
                            .size(30.dp)
                            .alpha(ContentAlpha.medium)
                            .rotate(
                                animateFloatAsState(
                                    targetValue = if (isExpanded) 180f else 0f
                                ).value
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { isExpanded = !isExpanded }
                    )
                }
            }
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