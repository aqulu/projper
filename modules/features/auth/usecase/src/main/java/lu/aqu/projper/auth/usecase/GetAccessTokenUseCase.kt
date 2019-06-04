package lu.aqu.projper.auth.usecase

interface GetAccessTokenUseCase {
    fun invoke(): String?
}
