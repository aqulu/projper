package lu.aqu.projper.auth.infra.api

import dagger.Module
import dagger.Provides
import lu.aqu.projper.auth.infra.localdatasource.AuthDataSource

@Module
class ApiModule {

    @Provides
    fun provideAuthApiClient(): AuthApiClient = AuthDataSource
}
