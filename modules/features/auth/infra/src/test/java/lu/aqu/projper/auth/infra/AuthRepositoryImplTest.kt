package lu.aqu.projper.auth.infra

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.infra.api.AuthApiClient
import lu.aqu.projper.auth.infra.api.json.CredentialsJson
import lu.aqu.projper.auth.infra.api.json.LoginResponseJson
import lu.aqu.projper.auth.infra.prefs.UserPreferences
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AuthRepositoryImplTest {

    private lateinit var authApiClient: AuthApiClient
    private lateinit var userPreferences: UserPreferences

    private lateinit var authRepository: AuthRepository

    @Before
    fun before() {
        authApiClient = mock(AuthApiClient::class.java)

        userPreferences = object : UserPreferences {
            override var accessToken: String? = null
        }

        authRepository = AuthRepositoryImpl(authApiClient, userPreferences)
    }

    @Test
    fun testLogin_SetAccessTokenOnSuccess() {
        val email = "2b@yorha.com"
        val password = "glorytomankind"
        val token = "2bOrNot2b"
        `when`(authApiClient.loginAsync(CredentialsJson(email, password)))
            .thenReturn(CompletableDeferred(LoginResponseJson(1L, email, token)))

        var result: User? = null
        runBlocking {
            result = authRepository.login(email, password)
        }

        Assert.assertEquals(
            "access token in userPreference must be set after login",
            token,
            userPreferences.accessToken
        )

        Assert.assertEquals(
            User(User.Id(1L), email),
            result
        )
    }
}
