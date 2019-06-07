package lu.aqu.projper

import android.app.Application
import lu.aqu.core.di.ComponentHolder
import lu.aqu.core.di.Injector
import lu.aqu.projper.auth.hostservice.DaggerAccessTokenServiceComponent
import lu.aqu.projper.auth.login.di.DaggerLoginComponent
import lu.aqu.projper.auth.login.di.LoginComponent
import lu.aqu.projper.di.DaggerRetrofitComponent
import lu.aqu.projper.di.DaggerSharedPreferencesComponent
import lu.aqu.projper.project.DaggerProjectComponent
import lu.aqu.projper.project.ProjectComponent
import kotlin.reflect.KClass

class Projper : Application(), ComponentHolder {

    private lateinit var components: Map<KClass<*>, Injector>

    override fun onCreate() {
        super.onCreate()

        val sharedPreferencesComponent = DaggerSharedPreferencesComponent
            .builder()
            .application(this)
            .build()

        val accessTokenServiceComponent = DaggerAccessTokenServiceComponent.builder()
            .sharedPreferences(sharedPreferencesComponent.sharedPreferences())
            .build()

        val retrofitComponent = DaggerRetrofitComponent.builder()
            .accessTokenService(accessTokenServiceComponent.accessTokenService())
            .build()

        components = mapOf(
            ProjectComponent::class to DaggerProjectComponent.builder()
                .retrofit(retrofitComponent.retrofit())
                .build(),
            LoginComponent::class to DaggerLoginComponent.builder()
                .sharedPreferences(sharedPreferencesComponent.sharedPreferences())
                .build()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Injector> getComponent(componentClass: KClass<T>): T =
        components[componentClass] as T
}
