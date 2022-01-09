package com.jefisu.movist.features.presentation.add_edit

import androidx.compose.ui.focus.FocusState

sealed class AddEditEvent {
    data class EnteredTitle(val value: String) : AddEditEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEvent()
    data class EnteredDescription(val value: String) : AddEditEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditEvent()
    object SaveMovie : AddEditEvent()
}
