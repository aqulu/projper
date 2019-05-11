package lu.aqu.projper.project.domain

interface ProjectRepository {

    suspend fun findAll(): List<Project>

    suspend fun findById(id: Long): Project
}
