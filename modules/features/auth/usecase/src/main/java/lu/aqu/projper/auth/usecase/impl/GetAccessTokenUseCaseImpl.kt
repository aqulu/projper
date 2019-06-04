package lu.aqu.projper.auth.usecase.impl

import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.usecase.GetAccessTokenUseCase
import javax.inject.Inject

class GetAccessTokenUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetAccessTokenUseCase {

    override fun invoke(): String? = authRepository.getAccessToken()
}
