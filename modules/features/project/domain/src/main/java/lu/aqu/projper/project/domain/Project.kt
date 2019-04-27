package lu.aqu.projper.project.domain

data class Project(
    val id: Long,
    val name: String,
    val description: String,
    val features: List<String>,
    val tags: List<String>
)