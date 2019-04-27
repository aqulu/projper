package lu.aqu.projper.project.infra

import dagger.Binds
import dagger.Module
import lu.aqu.projper.project.domain.ProjectRepository

@Module
abstract class ProjectInfraModule {

    @Binds
    internal abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository
}