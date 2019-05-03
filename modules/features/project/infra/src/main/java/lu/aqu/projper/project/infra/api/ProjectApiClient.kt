package lu.aqu.projper.project.infra.api

import lu.aqu.projper.project.infra.api.json.ProjectJson

internal interface ProjectApiClient {

    // TODO change to Retrofit result
    fun findAll(): List<ProjectJson>
}
