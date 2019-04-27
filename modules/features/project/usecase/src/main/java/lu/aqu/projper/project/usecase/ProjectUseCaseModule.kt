package lu.aqu.projper.project.usecase

import dagger.Binds
import dagger.Module
import lu.aqu.projper.project.infra.ProjectInfraModule
import lu.aqu.projper.project.usecase.impl.FindProjectsUseCaseImpl

@Module(
    includes = [ProjectInfraModule::class]
)
abstract class ProjectUseCaseModule {

    @Binds
    internal abstract fun bindFindProjectsUseCase(impl: FindProjectsUseCaseImpl): FindProjectsUseCase
}