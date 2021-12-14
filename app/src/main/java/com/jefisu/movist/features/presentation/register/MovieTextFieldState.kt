package com.jefisu.movist.features.presentation.register

data class MovieTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)