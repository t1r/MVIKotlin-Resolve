package nn.nn.mvikotlin_resolve.permissions.ui

sealed class PermissionsEvent {
    object Back : PermissionsEvent()
    object AskLocationPermissions : PermissionsEvent()
}
