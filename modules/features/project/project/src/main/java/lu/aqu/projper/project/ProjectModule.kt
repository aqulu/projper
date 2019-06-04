package lu.aqu.projper.project

import dagger.Module
import lu.aqu.projper.project.infra.ProjectInfraModule
import lu.aqu.projper.project.usecase.ProjectUseCaseModule

@Module(
    includes = [
        ProjectUseCaseModule::class,
        ProjectInfraModule::class
    ]
)
class ProjectModule
