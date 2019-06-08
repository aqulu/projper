package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.BookmarkRepository
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.infra.api.BookmarksApiClient
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarksApiClient: BookmarksApiClient
) : BookmarkRepository {

    override suspend fun findAll(): List<Project> =
        bookmarksApiClient.findAllAsync()
            .await()
            .map(ProjectConverter::toModel)

    override suspend fun add(id: Project.Id) =
        bookmarksApiClient.addAsync(AddBookmarkQueryJson(id.value))
            .await()
            .let(ProjectConverter::toModel)

    override suspend fun remove(id: Project.Id): Project =
        bookmarksApiClient.removeAsync(id.value)
            .await()
            .let(ProjectConverter::toModel)
}