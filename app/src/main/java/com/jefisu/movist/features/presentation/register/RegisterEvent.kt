package com.jefisu.movist.features.presentation.register

import androidx.compose.ui.focus.FocusState

sealed class RegisterEvent {
    data class EnteredTitle(val value: String) : RegisterEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredDescription(val value: String) : RegisterEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : RegisterEvent()
    data class SaveMovie(val id: Int? = null) : RegisterEvent()
}
