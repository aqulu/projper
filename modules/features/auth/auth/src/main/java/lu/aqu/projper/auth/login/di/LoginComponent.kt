package lu.aqu.projper.auth.login.di

import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.AuthModule
import lu.aqu.projper.auth.login.LoginFragment

@Component(
    modules = [AuthModule::class]
)
@FragmentScope
interface LoginComponent : AndroidInjector<LoginFragment>, Injector
