package lu.aqu.projper.auth.hostservice

import lu.aqu.projper.auth.usecase.GetAccessTokenUseCase
import javax.inject.Inject

internal class AccessTokenServiceImpl @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : AccessTokenService {

    override fun getAccessToken(): String? =
        getAccessTokenUseCase.invoke()
}
