package lu.aqu.projper.auth

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import lu.aqu.core.util.Constants
import lu.aqu.projper.auth.usecase.AuthUseCaseModule

@Module(
    includes = [AuthUseCaseModule::class]
)
class AuthModule(private val context: Context) {

    @Provides
    fun sharedPreferences(): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
}
