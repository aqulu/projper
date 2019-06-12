package lu.aqu.projper.di

import android.app.Activity
import androidx.fragment.app.Fragment
import lu.aqu.core.di.ComponentHolder

val Fragment.componentHolder: ComponentHolder
    get() = requireContext().applicationContext as? ComponentHolder
        ?: throw IllegalStateException("application must implement ComponentHolder interface")

val Activity.componentHolder: ComponentHolder
    get() = applicationContext as? ComponentHolder
        ?: throw IllegalStateException("application must implement ComponentHolder interface")
