package lu.aqu.projper.auth.hostservice

interface AccessTokenService {

    /**
     * @return the access token of the currently logged in user
     */
    fun getAccessToken(): String?
}
