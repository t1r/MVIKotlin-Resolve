package nn.nn.mvikotlin_resolve.permissions.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import nn.nn.mvikotlin_resolve.R
import nn.nn.mvikotlin_resolve.permissions.store.PermissionsStoreFactory
import timber.log.Timber

class PermissionsFragment : Fragment(R.layout.fragment_permissions) {

    private val controller: PermissionsController = PermissionsController(
        PermissionsStoreFactory(
            DefaultStoreFactory
        ).create(), this
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.onViewCreated(
            view = PermissionsViewImpl(view),
            lifecycle = lifecycle.asMviLifecycle(),
            viewLifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle()
        )
        initUi()
    }

    fun askLocationPermissions() {
        Timber.d("DEBDEB showRationaleDeviceCurrentLocation")
    }

    private fun initUi() {
//        mapView?.getMapAsync(getOnMapReadyCallback())
        onOnMapReady()
    }

    private fun onOnMapReady() {
//        super.setupGoogleMap(googleMap)
        controller.onOnMapReady()
        Timber.d("DEBDEB onOnMapReady")
    }
}