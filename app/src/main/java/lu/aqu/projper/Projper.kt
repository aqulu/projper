package lu.aqu.projper

import android.app.Application
import lu.aqu.core.di.ComponentHolder
import lu.aqu.core.di.Component
import lu.aqu.projper.auth.AuthComponent
import lu.aqu.projper.auth.DaggerAuthComponent
import lu.aqu.projper.di.DaggerRetrofitComponent
import lu.aqu.projper.di.DaggerSharedPreferencesComponent
import lu.aqu.projper.project.DaggerProjectComponent
import lu.aqu.projper.project.ProjectComponent
import kotlin.reflect.KClass

class Projper : Application(), ComponentHolder {

    private lateinit var components: Map<KClass<*>, Component>

    override fun onCreate() {
        super.onCreate()

        val sharedPreferencesComponent = DaggerSharedPreferencesComponent
            .builder()
            .application(this)
            .build()

        val authComponent = DaggerAuthComponent.builder()
            .sharedPreferences(sharedPreferencesComponent.sharedPreferences())
            .build()

        val retrofitComponent = DaggerRetrofitComponent.builder()
            .accessTokenService(authComponent.accessTokenService())
            .build()

        components = mapOf(
            AuthComponent::class to authComponent,
            ProjectComponent::class to DaggerProjectComponent.builder()
                .retrofit(retrofitComponent.retrofit())
                .build()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Component> getComponent(componentClass: KClass<T>): T =
        components[componentClass] as T
}
