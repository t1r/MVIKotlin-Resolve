package nn.nn.mvikotlin_resolve.permissions.ui

import nn.nn.mvikotlin_resolve.permissions.store.PermissionsStore

internal object Mappers {
    val viewEventToIntent: PermissionsView.ViewEvent.() -> PermissionsStore.Intent = {
        when (this) {
            is PermissionsView.ViewEvent.OnBackClicked -> PermissionsStore.Intent.Back
        }
    }

    val labelToEvent: PermissionsStore.Label.() -> PermissionsEvent = {
        when (this) {
            is PermissionsStore.Label.Back -> PermissionsEvent.Back
            is PermissionsStore.Label.AskLocationPermissions -> PermissionsEvent.AskLocationPermissions
        }
    }

    val stateToViewModel: PermissionsStore.State.() -> PermissionsView.ViewModel = {
        PermissionsView.ViewModel(
            isProgressVisible = false
        )
    }
}