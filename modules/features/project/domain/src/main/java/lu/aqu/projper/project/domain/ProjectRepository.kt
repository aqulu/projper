package lu.aqu.projper.project.domain

interface ProjectRepository {
    fun findAll(): List<Project>
}