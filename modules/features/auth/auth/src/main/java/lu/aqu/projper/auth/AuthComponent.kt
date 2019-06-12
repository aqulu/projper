package lu.aqu.projper.auth

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.hostservice.AccessTokenService
import lu.aqu.projper.auth.hostservice.HostServiceModule
import lu.aqu.projper.auth.login.LoginViewModel

@Component(
    modules = [
        HostServiceModule::class,
        AuthModule::class
    ]
)
interface AuthComponent : Injector {

    fun loginViewModelFactory(): LoginViewModel.Factory

    fun accessTokenService(): AccessTokenService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun sharedPreferences(sharedPreferences: SharedPreferences): Builder

        fun build(): AuthComponent
    }
}
