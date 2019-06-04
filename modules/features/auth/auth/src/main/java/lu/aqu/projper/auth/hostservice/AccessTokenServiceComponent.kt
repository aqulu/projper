package lu.aqu.projper.auth.hostservice

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component

@Component(modules = [HostServiceModule::class])
interface AccessTokenServiceComponent {

    fun accessTokenService(): AccessTokenService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun sharedPreferences(sharedPreferences: SharedPreferences): Builder

        fun build(): AccessTokenServiceComponent
    }
}

