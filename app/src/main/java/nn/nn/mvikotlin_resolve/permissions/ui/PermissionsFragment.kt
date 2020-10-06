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
        initUi(view)
    }

    fun askLocationPermissions() {
        Timber.d("DEBDEB askLocationPermissions")
    }

    private fun initUi(view: View) {
//        mapView?.getMapAsync(getOnMapReadyCallback())
        view.post { onOnMapReady() } //labels are lost without post
    }

    private fun onOnMapReady() {
//        super.setupGoogleMap(googleMap)
        controller.onOnMapReady()
        Timber.d("DEBDEB onOnMapReady")
    }
}