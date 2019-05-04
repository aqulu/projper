package lu.aqu.projper.project.infra.api

import dagger.Module
import dagger.Provides
import lu.aqu.projper.project.infra.api.localdatasource.ProjectDataSource

@Module
internal class ApiModule {

    // TODO change to retrofit
    @Provides
    fun provideProjectApiClient(): ProjectApiClient =
        ProjectDataSource
}
