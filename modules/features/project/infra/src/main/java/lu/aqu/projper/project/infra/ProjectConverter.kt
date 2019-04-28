package lu.aqu.projper.project.infra

import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.infra.api.json.ProjectJson

internal object ProjectConverter {

    fun toModel(json: ProjectJson): Project =
        Project(
            id = Project.Id(json.id),
            name = json.name,
            description = json.description,
            features = json.features,
            tags = json.tags
        )
}
