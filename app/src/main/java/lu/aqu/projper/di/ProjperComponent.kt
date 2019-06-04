package lu.aqu.projper.di

import android.app.Application
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [
        ApiModule::class,
        SharedPreferencesModule::class
    ]
)
interface ProjperComponent {

    fun sharedPreferences(): SharedPreferences

    fun retrofit(): Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ProjperComponent
    }
}
