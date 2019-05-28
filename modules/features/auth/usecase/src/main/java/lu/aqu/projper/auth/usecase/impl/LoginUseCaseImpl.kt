package lu.aqu.projper.auth.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.CoroutineUseCaseWithParameterAbs
import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.usecase.LoginQuery
import lu.aqu.projper.auth.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    mainDispatcher: CoroutineDispatcher,
    workDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : CoroutineUseCaseWithParameterAbs<LoginQuery, User>(mainDispatcher, workDispatcher), LoginUseCase {

    override suspend fun doInBackground(param: LoginQuery): User =
        authRepository.login(param.email, param.password)
}
