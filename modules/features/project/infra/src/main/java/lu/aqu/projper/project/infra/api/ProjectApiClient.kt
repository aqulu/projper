package lu.aqu.projper.project.infra.api

import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.json.ProjectJson
import retrofit2.http.GET

internal interface ProjectApiClient {

    @GET("projects.json")
    fun findAllAsync(): Deferred<List<ProjectJson>>
}
