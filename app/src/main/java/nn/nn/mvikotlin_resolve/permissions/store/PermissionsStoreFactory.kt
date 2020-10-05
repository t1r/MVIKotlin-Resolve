package nn.nn.mvikotlin_resolve.permissions.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import nn.nn.mvikotlin_resolve.permissions.store.PermissionsStore.*
import timber.log.Timber

class PermissionsStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): PermissionsStore =
        object : PermissionsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PermissionsStore",
            initialState = State(),
            executorFactory = ::PermissionsExecutor,
            reducer = PermissionsReducer
        ) {}

    private inner class PermissionsExecutor
        : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeIntent(
            intent: Intent,
            getState: () -> State
        ) = when (intent) {
            is Intent.Back -> publish(Label.Back)

            /* Map */
            is Intent.MapReady -> resolveInitMapReady()
        }

        private fun resolveInitMapReady() {
            Timber.d("DEBDEB resolveInitMapReady")
            publish(Label.AskLocationPermissions)
        }
    }

    private object PermissionsReducer : Reducer<State, Result> {
        override fun State.reduce(
            result: Result
        ): State = when (result) {
            is Result.AskedLocationPermissionsFirstTime -> copy(
                isAskLocationPermissionsFirstTime = false
            )
        }
    }
}