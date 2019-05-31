package lu.aqu.projper.auth.infra

import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.infra.api.AuthApiClient
import lu.aqu.projper.auth.infra.api.json.CredentialsJson
import lu.aqu.projper.auth.infra.prefs.UserPreferences
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiClient: AuthApiClient,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override fun getAccessToken(): String? =
        userPreferences.accessToken

    override suspend fun login(email: String, password: String): User {
        val loginResponse = authApiClient
            .loginAsync(CredentialsJson(email, password))
            .await()

        userPreferences.accessToken = loginResponse.token

        return LoginResponseConverter.toUser(loginResponse)
    }
}
