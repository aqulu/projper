package lu.aqu.projper.auth.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.infra.AuthInfraModule
import lu.aqu.projper.auth.usecase.impl.GetAccessTokenUseCaseImpl
import lu.aqu.projper.auth.usecase.impl.LoginUseCaseImpl

@Module(
    includes = [AuthInfraModule::class]
)
class AuthUseCaseModule {

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCaseImpl(Dispatchers.Main, Dispatchers.IO, authRepository)

    @Provides
    fun provideGetAccessTokenUseCase(authRepository: AuthRepository): GetAccessTokenUseCase =
        GetAccessTokenUseCaseImpl(Dispatchers.Main, Dispatchers.Default, authRepository)
}
