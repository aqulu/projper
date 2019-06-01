package lu.aqu.projper.auth.infra.localdatasource

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import lu.aqu.projper.auth.infra.api.AuthApiClient
import lu.aqu.projper.auth.infra.api.json.CredentialsJson
import lu.aqu.projper.auth.infra.api.json.LoginResponseJson
import lu.aqu.projper.auth.infra.api.json.LogoutResponseJson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.UUID

object AuthDataSource : AuthApiClient {

    override fun loginAsync(credentials: CredentialsJson): Deferred<LoginResponseJson> {
        if (credentials.email != TEST_EMAIL || credentials.password != TEST_PASSWORD) {
            val response = Response.error<String>(404, ResponseBody.create(null, "user not found"))
            throw HttpException(response)
        }

        return CompletableDeferred(
            LoginResponseJson(
                1L,
                TEST_EMAIL,
                UUID.randomUUID().toString()
            )
        )
    }

    override fun logoutAsync(): Deferred<LogoutResponseJson> =
        CompletableDeferred(LogoutResponseJson(""))

    private const val TEST_EMAIL = "jim@knopf.nl"
    private const val TEST_PASSWORD = "emma"
}
