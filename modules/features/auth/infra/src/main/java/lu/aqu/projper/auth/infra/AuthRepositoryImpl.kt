package lu.aqu.projper.auth.infra

import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.domain.User

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
