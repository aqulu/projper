package lu.aqu.projper.project.infra.api.json

internal data class ProjectJson(
    val id: Long,
    val name: String,
    val description: String,
    val features: List<String>,
    val tags: List<String>
)
