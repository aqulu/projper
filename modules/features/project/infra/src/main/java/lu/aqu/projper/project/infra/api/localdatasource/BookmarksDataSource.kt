package lu.aqu.projper.project.infra.api.localdatasource

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.BookmarksApiClient
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import lu.aqu.projper.project.infra.api.json.ProjectJson

/**
 * mock booksmarks datasource
 */
internal class BookmarksDataSource : BookmarksApiClient {

    override fun findAllAsync(): Deferred<List<ProjectJson>> =
        CompletableDeferred(
            ProjectDataSource.projects.let {
                it.take(it.size / 2)
            }
        )

    override fun addAsync(addBookmarkQueryJson: AddBookmarkQueryJson): Deferred<ProjectJson> =
        CompletableDeferred(
            ProjectDataSource.projects.first { it.id == addBookmarkQueryJson.id }
        )

    override fun removeAsync(id: Long): Deferred<ProjectJson> =
        CompletableDeferred(
            ProjectDataSource.projects.first { it.id == id }
        )
}
