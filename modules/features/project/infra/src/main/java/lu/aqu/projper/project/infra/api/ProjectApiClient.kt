package lu.aqu.projper.project.infra.api

import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.json.ProjectJson
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProjectApiClient {

    @GET("projects.json")
    fun findAllAsync(): Deferred<List<ProjectJson>>

    @GET("projects/{id}.json")
    fun findByIdAsync(
        @Path("id") id: Long
    ): Deferred<ProjectJson>
}
