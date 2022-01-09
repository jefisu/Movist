package com.jefisu.movist.features.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jefisu.movist.features.presentation.util.Screen
import com.jefisu.movist.ui.theme.Purple500
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1000L)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Purple500),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Icon splash",
            tint = Color.White,
            modifier = Modifier
                .size(130.dp)
                .alpha(
                    animateFloatAsState(
                        targetValue = if (startAnimation) 1f else 0f,
                        animationSpec = tween(durationMillis = 1500)
                    ).value
                )
        )
    }
}