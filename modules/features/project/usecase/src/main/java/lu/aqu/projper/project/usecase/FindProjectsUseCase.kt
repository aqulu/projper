package lu.aqu.projper.project.usecase

import lu.aqu.core.coroutine.CoroutineUseCase
import lu.aqu.projper.project.domain.Project

interface FindProjectsUseCase : CoroutineUseCase<List<Project>>
