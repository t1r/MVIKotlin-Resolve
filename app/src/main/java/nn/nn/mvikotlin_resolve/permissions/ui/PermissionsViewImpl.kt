package nn.nn.mvikotlin_resolve.permissions.ui

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import nn.nn.mvikotlin_resolve.R
import nn.nn.mvikotlin_resolve.permissions.ui.PermissionsView.ViewEvent
import nn.nn.mvikotlin_resolve.permissions.ui.PermissionsView.ViewModel

class PermissionsViewImpl(
    view: View
) : BaseMviView<ViewModel, ViewEvent>(), PermissionsView {

    private val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

    init {
        toolbar.setOnClickListener { dispatch(ViewEvent.OnBackClicked) }
    }

    override val renderer: ViewRenderer<ViewModel> = diff {
    }
}