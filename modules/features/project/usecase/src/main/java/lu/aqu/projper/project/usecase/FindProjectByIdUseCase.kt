package lu.aqu.projper.project.usecase

import lu.aqu.core.coroutine.CoroutineUseCaseWithParameter
import lu.aqu.projper.project.domain.Project

interface FindProjectByIdUseCase : CoroutineUseCaseWithParameter<Project.Id, Project>
