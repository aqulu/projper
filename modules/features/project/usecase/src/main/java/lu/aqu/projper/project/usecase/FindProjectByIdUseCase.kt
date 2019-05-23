package lu.aqu.projper.project.usecase

import lu.aqu.core.coroutine.ParametrizedCoroutineUseCase
import lu.aqu.projper.project.domain.Project

interface FindProjectByIdUseCase : ParametrizedCoroutineUseCase<Project.Id, Project>
