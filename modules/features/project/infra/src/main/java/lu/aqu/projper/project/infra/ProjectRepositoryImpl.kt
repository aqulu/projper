package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.infra.api.ProjectApiClient
import javax.inject.Inject

internal class ProjectRepositoryImpl @Inject constructor(
    private val projectApiClient: ProjectApiClient
) : ProjectRepository {

    override fun findAll(): List<Project> =
        projectApiClient
            .findAll()
            .map(ProjectConverter::toModel)
}
