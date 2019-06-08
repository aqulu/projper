package lu.aqu.projper.project.infra.api.localdatasource

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import lu.aqu.core.util.Constants
import lu.aqu.projper.project.infra.api.BookmarksApiClient
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import lu.aqu.projper.project.infra.api.json.ProjectJson
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

/**
 * mock booksmarks datasource
 */
internal class BookmarksDataSource(
    private val retrofit: Retrofit
) : BookmarksApiClient {

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

    private fun isLoggedIn() =
        !retrofit.callFactory()
            .newCall(Request.Builder().url("https://ddg.gg/").build())
            .request()
            .header(Constants.Api.AUTH_HEADER)
            .isNullOrEmpty()
}
