package lu.aqu.projper.project.infra.api.localdatasource

import lu.aqu.projper.project.infra.api.ProjectApiClient
import lu.aqu.projper.project.infra.api.json.ProjectJson

internal object ProjectDataSource : ProjectApiClient {

    override fun findAll(): List<ProjectJson> =
        listOf(
            ProjectJson(
                id = 1L,
                name = "Hello",
                description = "Hello",
                features = emptyList(),
                tags = emptyList()
            ),
            ProjectJson(
                id = 2L,
                name = "World!",
                description = "World!",
                features = emptyList(),
                tags = emptyList()
            )
        )
}
