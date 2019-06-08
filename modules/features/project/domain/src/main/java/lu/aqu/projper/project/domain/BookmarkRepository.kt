package lu.aqu.projper.project.domain

interface BookmarkRepository {

    /**
     * find all bookmarks for the currently logged in user
     * @return list of bookmarks
     */
    suspend fun findAll(): List<Project>

    /**
     * add project with the specified id to bookmarks
     * @return the added project
     */
    suspend fun add(id: Project.Id): Project

    /**
     * remove project with the specified id from bookmarks
     * @return the removed project
     */
    suspend fun remove(id: Project.Id): Project
}
