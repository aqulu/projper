package lu.aqu.projper.project.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.ParametrizedCoroutineUseCaseAbs
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.usecase.FindProjectByIdUseCase
import javax.inject.Inject

internal class FindProjectByIdUseCaseImpl @Inject constructor(
    private val projectRepository: ProjectRepository,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : ParametrizedCoroutineUseCaseAbs<Project.Id, Project>(mainDispatcher, ioDispatcher), FindProjectByIdUseCase {

    override suspend fun executeAsync(param: Project.Id): Project =
        projectRepository.findById(param.value)
}
