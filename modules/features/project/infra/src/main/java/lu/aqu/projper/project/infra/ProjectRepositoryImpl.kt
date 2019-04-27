package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.domain.ProjectRepository

class ProjectRepositoryImpl : ProjectRepository {

    override fun findAll(): List<Project> =
        emptyList()
}