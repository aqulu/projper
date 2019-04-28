package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository
import javax.inject.Inject

internal class ProjectRepositoryImpl @Inject constructor() : ProjectRepository {

    override fun findAll(): List<Project> =
        emptyList()
}
