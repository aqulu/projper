package lu.aqu.projper.auth.infra.api

import kotlinx.coroutines.Deferred
import lu.aqu.projper.auth.infra.api.json.CredentialsJson
import lu.aqu.projper.auth.infra.api.json.LoginResponseJson
import lu.aqu.projper.auth.infra.api.json.LogoutResponseJson
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {

    @POST("authenticate")
    fun loginAsync(
        @Body credentials: CredentialsJson
    ): Deferred<LoginResponseJson>

    @POST("logout")
    fun logoutAsync(): Deferred<LogoutResponseJson>
}
