package lu.aqu.projper.auth.login.di

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.AuthModule
import lu.aqu.projper.auth.login.LoginFragment

@Component(modules = [AuthModule::class])
@FragmentScope
interface LoginComponent : AndroidInjector<LoginFragment>, Injector {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun sharedPreferences(sharedPreferences: SharedPreferences): Builder

        fun build(): LoginComponent
    }
}
