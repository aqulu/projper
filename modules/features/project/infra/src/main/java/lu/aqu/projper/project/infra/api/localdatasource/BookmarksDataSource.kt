package lu.aqu.projper.project.infra.api.localdatasource

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.BookmarksApiClient
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import lu.aqu.projper.project.infra.api.json.ProjectJson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

/**
 * mock booksmarks datasource
 */
internal class BookmarksDataSource : BookmarksApiClient {

    @Volatile
    private var requestCount = 0

    override fun findAllAsync(): Deferred<List<ProjectJson>> {
        if (!isLoggedIn()) {
            val body = ResponseBody.create(null, "not logged in")
            throw HttpException(Response.error<String>(403, body))
        }

        return CompletableDeferred(emptyList())
    }

    override fun addAsync(addBookmarkQueryJson: AddBookmarkQueryJson): Deferred<ProjectJson> {
        if (!isLoggedIn()) {
            val body = ResponseBody.create(null, "not logged in")
            throw HttpException(Response.error<String>(403, body))
        }

        return CompletableDeferred(
            ProjectDataSource.projects.first { it.id == addBookmarkQueryJson.id }
        )
    }

    override fun removeAsync(id: Long): Deferred<ProjectJson> {
        if (!isLoggedIn()) {
            val body = ResponseBody.create(null, "not logged in")
            throw HttpException(Response.error<String>(403, body))
        }

        return CompletableDeferred(
            ProjectDataSource.projects.first { it.id == id }
        )
    }

    /**
     * returns false for the first request and true after
     */
    private fun isLoggedIn(): Boolean =
        (requestCount > 0)
            .also {
                synchronized(this) {
                    requestCount++
                }
            }
}
