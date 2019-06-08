package lu.aqu.projper.project.infra

import dagger.Binds
import dagger.Module
import lu.aqu.projper.project.domain.BookmarkRepository
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.infra.api.ApiModule

@Module(
    includes = [ApiModule::class]
)
abstract class ProjectInfraModule {

    @Binds
    internal abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository

    @Binds
    internal abstract fun bindBookmarkRepository(impl: BookmarkRepositoryImpl): BookmarkRepository
}
