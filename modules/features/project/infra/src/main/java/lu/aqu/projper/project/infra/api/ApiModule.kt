package lu.aqu.projper.project.infra.api

import dagger.Module
import dagger.Provides
import lu.aqu.projper.project.infra.api.localdatasource.BookmarksDataSource
import lu.aqu.projper.project.infra.api.localdatasource.ProjectDataSource
import retrofit2.Retrofit

@Module
internal class ApiModule {

    @Provides
    fun provideProjectApiClient(retrofit: Retrofit): ProjectApiClient =
        ProjectDataSource

    @Provides
    fun provideBookmarksApiClient(retrofit: Retrofit): BookmarksApiClient =
        BookmarksDataSource()
}
