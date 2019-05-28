package lu.aqu.projper.auth.infra

import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.infra.api.AuthApiClient
import lu.aqu.projper.auth.infra.api.json.CredentialsJson
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiClient: AuthApiClient
) : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        val loginResponse = authApiClient
            .loginAsync(CredentialsJson(email, password))
            .await()

        // TODO store loginResponse.token

        return LoginResponseConverter.toUser(loginResponse)
    }
}
