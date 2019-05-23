package lu.aqu.projper.project.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.infra.ProjectInfraModule
import lu.aqu.projper.project.usecase.impl.FindProjectByIdUseCaseImpl
import lu.aqu.projper.project.usecase.impl.FindProjectsUseCaseImpl

@Module(
    includes = [ProjectInfraModule::class]
)
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
}
