package lu.aqu.projper.auth.infra

import dagger.Binds
import dagger.Module
import lu.aqu.projper.auth.domain.AuthRepository

@Module
abstract class InfraModule {

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
