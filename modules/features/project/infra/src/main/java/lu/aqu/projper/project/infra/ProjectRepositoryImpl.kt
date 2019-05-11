package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.infra.api.ProjectApiClient
import javax.inject.Inject

internal class ProjectRepositoryImpl @Inject constructor(
    private val projectApiClient: ProjectApiClient
) : ProjectRepository {

    override suspend fun findAll(): List<Project> =
        projectApiClient
            .findAllAsync()
            .await()
            .map(ProjectConverter::toModel)

    override suspend fun findById(id: Long): Project =
        projectApiClient
            .findByIdAsync(id)
            .await()
            .let(ProjectConverter::toModel)
}
