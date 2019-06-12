package lu.aqu.projper.auth.login

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import lu.aqu.core.di.FragmentScope
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.AuthModule

@Component(modules = [AuthModule::class])
@FragmentScope
interface LoginComponent : Injector {

    fun loginViewModelFactory(): LoginViewModel.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun sharedPreferences(sharedPreferences: SharedPreferences): Builder

        fun build(): LoginComponent
    }
}
