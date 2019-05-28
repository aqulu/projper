package lu.aqu.projper.auth.domain

interface AuthRepository {
    
    suspend fun login(email: String, password: String): User
}
