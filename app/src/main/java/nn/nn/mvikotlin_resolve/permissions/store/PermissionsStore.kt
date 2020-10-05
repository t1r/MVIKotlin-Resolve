package nn.nn.mvikotlin_resolve.permissions.store

import com.arkivanov.mvikotlin.core.store.Store
import nn.nn.mvikotlin_resolve.permissions.store.PermissionsStore.*

interface PermissionsStore : Store<Intent, State, Label> {

    data class State(
        val isAskLocationPermissionsFirstTime: Boolean = true
    )

    sealed class Intent {
        /* Map */
        object Back : Intent()
        object MapReady : Intent()
    }

    sealed class Action

    sealed class Label {
        object Back : Label()
        object AskLocationPermissions : Label()
    }

    sealed class Result {
        object AskedLocationPermissionsFirstTime : Result()
    }
}

