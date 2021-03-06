package com.jefisu.movist.features.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.jefisu.movist.features.domain.model.Movie
import com.jefisu.movist.ui.theme.spaces

@ExperimentalMaterialApi
@Composable
fun MoveItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    cutCornerSize: Dp = 20.dp,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: (Movie) -> Unit = {}
) {
    val color = MaterialTheme.colors.onSurface
    val darkMode = isSystemInDarkTheme()
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath) {
                drawRoundRect(
                    color = if (darkMode) color.copy(0.1f)
                            else Color.DarkGray.copy(0.2f),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(Color.Black.value.toInt(), 0x77FFFFFF, 0.7f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), 0f),
                    size = Size(cutCornerSize.toPx(), cutCornerSize.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spaces.small)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
                color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (movie.description.isNotBlank()) {
                    Text(
                        text = movie.description,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        maxLines = 1
                    )
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete icon",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onDeleteClick(movie) }
                    )
                }
            }
        }
    }
}