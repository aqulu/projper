package lu.aqu.projper.di

import android.app.Application
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SharedPreferencesModule::class]
)
interface SharedPreferencesComponent {

    fun sharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): SharedPreferencesComponent
    }
}
