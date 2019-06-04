package lu.aqu.projper.auth

import dagger.Module
import lu.aqu.projper.auth.infra.AuthInfraModule
import lu.aqu.projper.auth.usecase.AuthUseCaseModule

@Module(
    includes = [
        AuthUseCaseModule::class,
        AuthInfraModule::class
    ]
)
class AuthModule
