package lu.aqu.projper.project.domain

interface BookmarkRepository {

    /**
     * find all bookmarks for the currently logged in user
     * @return list of bookmarked project
     * @throws NotAuthorizedException if user is not authenticated or authorized to perform this action
     */
    suspend fun findAll(): List<Project>

    /**
     * add project with the specified id to bookmarks
     * @return the added project
     * @throws NoSuchElementException if no project could be found for this [id]
     * @throws NotAuthorizedException if user is not authenticated or authorized to perform this action
     */
    suspend fun add(id: Project.Id): Project

    /**
     * remove project with the specified id from bookmarks
     * @return the removed project
     * @throws NoSuchElementException if no bookmarked project for this [id] could be found
     * @throws NotAuthorizedException if user is not authenticated or authorized to perform this action
     */
    suspend fun remove(id: Project.Id): Project
}
