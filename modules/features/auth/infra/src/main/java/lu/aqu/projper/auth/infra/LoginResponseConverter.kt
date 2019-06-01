package lu.aqu.projper.auth.infra

import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.infra.api.json.LoginResponseJson

object LoginResponseConverter {

    fun toUser(json: LoginResponseJson) =
        User(
            id = User.Id(json.id),
            email = json.email
        )
}
