package lu.aqu.projper.project.infra.api.localdatasource

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import lu.aqu.projper.project.infra.api.ProjectApiClient
import lu.aqu.projper.project.infra.api.json.ProjectJson

internal object ProjectDataSource : ProjectApiClient {

    override fun findAllAsync(): Deferred<List<ProjectJson>> =
        CompletableDeferred(projects)

    override fun findByIdAsync(id: Long): Deferred<ProjectJson> =
        CompletableDeferred(projects.first { it.id == id })

    private val projects =
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
