package lu.aqu.projper.project.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.CoroutineUseCaseAbs
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

internal class FindProjectsUseCaseImpl @Inject constructor(
    private val projectRepository: ProjectRepository,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCaseAbs<List<Project>>(mainDispatcher, ioDispatcher), FindProjectsUseCase {

    override suspend fun doInBackground(): List<Project> =
        projectRepository.findAll()
}
