package lu.aqu.projper.auth.usecase

import lu.aqu.core.coroutine.CoroutineUseCaseWithParameter
import lu.aqu.projper.auth.domain.User

interface LoginUseCase : CoroutineUseCaseWithParameter<LoginQuery, User>

data class LoginQuery(
    val email: String,
    val password: String
)
