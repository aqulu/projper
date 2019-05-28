package lu.aqu.projper.auth.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lu.aqu.projper.auth.domain.AuthRepository
import lu.aqu.projper.auth.infra.InfraModule
import lu.aqu.projper.auth.usecase.impl.LoginUseCaseImpl

@Module(
    includes = [InfraModule::class]
)
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCaseImpl(Dispatchers.Main, Dispatchers.IO, authRepository)
}
