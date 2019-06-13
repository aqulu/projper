package lu.aqu.projper.auth.domain

interface AuthRepository {

    /**
     * @return the current user's access token or <code>null</code> if not authenticated
     */
    fun getAccessToken(): String?

    /**
     * attempts to login with the supplied credentials
     * @return the logged in user
     * @throws Exception if the login operation failed
     */
    suspend fun login(email: String, password: String): User
}
