package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import lu.aqu.projper.project.infra.api.ProjectApiClient
import retrofit2.HttpException
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
        try {
            projectApiClient
                .findByIdAsync(id)
                .await()
                .let(ProjectConverter::toModel)
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> throw NoSuchElementException("project with id $id not found")
                else -> throw e
            }
        }
}
