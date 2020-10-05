package nn.nn.mvikotlin_resolve.permissions.ui

import com.arkivanov.mvikotlin.core.view.MviView
import nn.nn.mvikotlin_resolve.permissions.ui.PermissionsView.ViewEvent
import nn.nn.mvikotlin_resolve.permissions.ui.PermissionsView.ViewModel

interface PermissionsView : MviView<ViewModel, ViewEvent> {

    data class ViewModel(
        val isProgressVisible: Boolean
    )

    sealed class ViewEvent {
        object OnBackClicked : ViewEvent()
    }
}