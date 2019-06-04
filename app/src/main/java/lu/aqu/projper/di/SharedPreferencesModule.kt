package lu.aqu.projper.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import lu.aqu.core.util.Constants

@Module
internal class SharedPreferencesModule {

    @Provides
    fun sharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
}
