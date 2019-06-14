package lu.aqu.projper.project.domain

interface ProjectRepository {

    /**
     * @return list of all projects
     */
    suspend fun findAll(): List<Project>

    /**
     * @return project
     * @throws NoSuchElementException if no project could be found for the supplied id
     */
    suspend fun findById(id: Long): Project
}
