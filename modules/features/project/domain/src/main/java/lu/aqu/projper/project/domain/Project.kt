package lu.aqu.projper.project.domain

import lu.aqu.core.ddd.Entity
import lu.aqu.core.ddd.Identifier

data class Project(
    override val id: Id,
    val name: String,
    val description: String,
    val features: List<String>,
    val tags: List<String>
) : Entity<Project.Id> {

    data class Id(
        override val value: Long
    ) : Identifier<Long>
}
