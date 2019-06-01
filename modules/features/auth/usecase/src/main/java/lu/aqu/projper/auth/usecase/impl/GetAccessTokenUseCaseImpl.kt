package lu.aqu.projper.auth.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import lu.aqu.core.coroutine.CoroutineUseCaseAbs
import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.usecase.GetAccessTokenUseCase
import javax.inject.Inject

class GetAccessTokenUseCaseImpl @Inject constructor(
    mainDispatcher: CoroutineDispatcher,
    workDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : CoroutineUseCaseAbs<String?>(mainDispatcher, workDispatcher), GetAccessTokenUseCase {

    override suspend fun doInBackground(): String? =
        authRepository.getAccessToken()
}
