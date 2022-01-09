package com.jefisu.movist.features.presentation.add_edit

data class MovieTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)