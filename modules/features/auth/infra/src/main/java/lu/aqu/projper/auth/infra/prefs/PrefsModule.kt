package lu.aqu.projper.auth.infra.prefs

import dagger.Binds
import dagger.Module
import lu.aqu.projper.auth.infra.prefs.impl.UserPreferencesImpl

@Module
abstract class PrefsModule {

    @Binds
    abstract fun bindUserPreferences(impl: UserPreferencesImpl): UserPreferences
}
