package lu.aqu.projper.project.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lu.aqu.projper.project.domain.BookmarkRepository
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.usecase.impl.FindProjectByIdUseCaseImpl
import lu.aqu.projper.project.usecase.impl.FindProjectsUseCaseImpl
import lu.aqu.projper.project.usecase.impl.GetBookmarksUseCaseImpl

@Module
class ProjectUseCaseModule {

    @Provides
    internal fun provideFindProjectsUseCase(projectRepository: ProjectRepository): FindProjectsUseCase =
        FindProjectsUseCaseImpl(
            projectRepository,
            Dispatchers.Main,
            Dispatchers.IO
        )

    @Provides
    internal fun provideFindProjectByIdUseCase(projectRepository: ProjectRepository): FindProjectByIdUseCase =
        FindProjectByIdUseCaseImpl(
            projectRepository,
            Dispatchers.Main,
            Dispatchers.IO
        )

    @Provides
    internal fun provideGetBookmarksUseCase(bookmarkRepository: BookmarkRepository): GetBookmarksUseCase =
        GetBookmarksUseCaseImpl(
            bookmarkRepository,
            Dispatchers.Main,
            Dispatchers.IO
        )
}
