package lu.aqu.projper.project.infra.api

import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.json.AddBookmarkQueryJson
import lu.aqu.projper.project.infra.api.json.ProjectJson
import retrofit2.http.*

internal interface BookmarksApiClient {

    @GET("bookmarks")
    fun findAllAsync(): Deferred<List<ProjectJson>>

    @POST("bookmarks")
    fun addAsync(
        @Body addBookmarkQueryJson: AddBookmarkQueryJson
    ): Deferred<ProjectJson>

    @DELETE("bookmarks/{id}")
    fun removeAsync(
        @Path("id") id: Long
    ): Deferred<ProjectJson>
}
