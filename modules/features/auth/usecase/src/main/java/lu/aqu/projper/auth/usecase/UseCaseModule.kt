package lu.aqu.projper.auth.usecase

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lu.aqu.projper.auth.usecase.impl.LoginUseCaseImpl

@Module
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(): LoginUseCase = LoginUseCaseImpl(Dispatchers.Main, Dispatchers.IO)
}
