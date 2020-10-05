package nn.nn.mvikotlin_resolve

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import nn.nn.mvikotlin_resolve.permissions.ui.PermissionsFragment

class MainActivity : AppCompatActivity() {

    private val fragmentFactory = MainActivityFragmentFactoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragmentFactory.permissionsFragment())
                .commit()
        }
    }

    private inner class MainActivityFragmentFactoryImpl : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
            when (loadFragmentClass(classLoader, className)) {
                PermissionsFragment::class.java -> permissionsFragment()
                else -> super.instantiate(classLoader, className)
            }

        fun permissionsFragment(): PermissionsFragment =
            PermissionsFragment()
    }
}