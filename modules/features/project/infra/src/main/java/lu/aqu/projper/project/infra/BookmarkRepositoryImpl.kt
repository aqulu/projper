package lu.aqu.projper.project.infra

import lu.aqu.core.exception.NotAuthorizedException
import lu.aqu.projper.project.domain.BookmarkRepository
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.infra.api.BookmarksApiClient
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import retrofit2.HttpException
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarksApiClient: BookmarksApiClient
) : BookmarkRepository {

    override suspend fun findAll(): List<Project> =
        try {
            bookmarksApiClient.findAllAsync()
                .await()
                .map(ProjectConverter::toModel)
        } catch (e: HttpException) {
            when (e.code()) {
                401, 403 -> throw NotAuthorizedException()
                else -> throw e
            }
        }

    override suspend fun add(id: Project.Id) =
        try {
            bookmarksApiClient.addAsync(AddBookmarkQueryJson(id.value))
                .await()
                .let(ProjectConverter::toModel)
        } catch (e: HttpException) {
            when (e.code()) {
                401, 403 -> throw NotAuthorizedException()
                404 -> throw NoSuchElementException("project with id $id not found")
                else -> throw e
            }
        }

    override suspend fun remove(id: Project.Id): Project =
        try {
            bookmarksApiClient.removeAsync(id.value)
                .await()
                .let(ProjectConverter::toModel)
        } catch (e: HttpException) {
            when (e.code()) {
                401, 403 -> throw NotAuthorizedException()
                404 -> throw NoSuchElementException("bookmark for project with id $id not found")
                else -> throw e
            }
        }
}
