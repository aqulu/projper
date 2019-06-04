package lu.aqu.projper.project.infra.api

import dagger.Module
import dagger.Provides
import lu.aqu.projper.project.infra.api.localdatasource.ProjectDataSource
import retrofit2.Retrofit

@Module
internal class ApiModule {

    @Provides
    fun provideProjectApiClient(retrofit: Retrofit): ProjectApiClient =
        ProjectDataSource
}
