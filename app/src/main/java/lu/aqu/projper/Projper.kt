package lu.aqu.projper

import android.app.Application
import lu.aqu.core.di.ComponentHolder
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.AuthModule
import lu.aqu.projper.auth.login.di.DaggerLoginComponent
import lu.aqu.projper.auth.login.di.LoginComponent
import lu.aqu.projper.project.details.di.DaggerDetailsComponent
import lu.aqu.projper.project.details.di.DetailsComponent
import lu.aqu.projper.project.overview.di.DaggerOverviewComponent
import lu.aqu.projper.project.overview.di.OverviewComponent
import kotlin.reflect.KClass

class Projper : Application(), ComponentHolder {

    private lateinit var components: Map<KClass<*>, Injector>

    override fun onCreate() {
        super.onCreate()
        components = mapOf(
            OverviewComponent::class to DaggerOverviewComponent.create(),
            DetailsComponent::class to DaggerDetailsComponent.create(),
            LoginComponent::class to DaggerLoginComponent.builder().authModule(AuthModule(this)).build()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Injector> getComponent(componentClass: KClass<T>): T =
        components[componentClass] as T
}
