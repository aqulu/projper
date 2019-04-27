package lu.aqu.projper.project.usecase.impl

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

internal class FindProjectsUseCaseImpl @Inject constructor(
    private val projectRepository: ProjectRepository
) : FindProjectsUseCase {

    override fun execute(): List<Project> =
        projectRepository.findAll()
}
