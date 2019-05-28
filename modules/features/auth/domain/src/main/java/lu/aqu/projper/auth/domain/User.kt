package lu.aqu.projper.auth.domain

import lu.aqu.core.ddd.Entity
import lu.aqu.core.ddd.Identifier

data class User(
    override val id: Id,
    val email: String
) : Entity<User.Id> {

    data class Id(
        override val value: Long
    ) : Identifier<Long>
}
