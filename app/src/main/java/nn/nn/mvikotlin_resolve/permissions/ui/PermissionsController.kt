package nn.nn.mvikotlin_resolve.permissions.ui

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.view.MviView
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import nn.nn.mvikotlin_resolve.core.ext.mapNotNull
import nn.nn.mvikotlin_resolve.permissions.store.PermissionsStore
import nn.nn.mvikotlin_resolve.permissions.ui.Mappers.labelToEvent
import nn.nn.mvikotlin_resolve.permissions.ui.Mappers.stateToViewModel
import nn.nn.mvikotlin_resolve.permissions.ui.Mappers.viewEventToIntent
import timber.log.Timber

class PermissionsController constructor(
    private val store: PermissionsStore,
    private val fragment: PermissionsFragment
) {

    init {
        bind(fragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.CREATE_DESTROY) {
            store.labels.mapNotNull(labelToEvent) bindTo { consumeEvent(it) }
        }
    }

    fun onViewCreated(
        view: MviView<PermissionsView.ViewModel, PermissionsView.ViewEvent>,
        lifecycle: Lifecycle,
        viewLifecycle: Lifecycle
    ) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(stateToViewModel) bindTo view
        }

        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.mapNotNull(viewEventToIntent) bindTo store
        }

        lifecycle.doOnDestroy(store::dispose)
    }

    fun onBackPressed() {
        store.accept(PermissionsStore.Intent.Back)
    }

    fun onOnMapReady() {
        store.accept(PermissionsStore.Intent.MapReady)
    }

    private fun consumeEvent(event: PermissionsEvent) {
        when (event) {
            is PermissionsEvent.Back -> {
                //TOOD back
            }
            is PermissionsEvent.AskLocationPermissions -> {
                Timber.d("DEBDEB consume AskLocationPermissions label")
                fragment.askLocationPermissions()
            }
        }
    }
}