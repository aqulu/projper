package lu.aqu.projper.auth.domain

interface AuthRepository {

    fun getAccessToken(): String?

    suspend fun login(email: String, password: String): User
}
