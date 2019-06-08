package lu.aqu.projper.project.infra.api

import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import lu.aqu.projper.project.infra.api.json.ProjectJson
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

internal interface BookmarksApiClient {

    @GET("bookmarks")
    fun findAllAsync(): Deferred<List<ProjectJson>>

    @GET("bookmarks")
    fun addAsync(
        @Body addBookmarkQueryJson: AddBookmarkQueryJson
    ): Deferred<ProjectJson>

    @GET("bookmarks/{id}")
    fun removeAsync(
        @Path("id") id: Long
    ): Deferred<ProjectJson>
}
