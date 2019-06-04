package lu.aqu.projper.auth.hostservice

import dagger.Binds
import dagger.Module
import lu.aqu.projper.auth.AuthModule

@Module(includes = [AuthModule::class])
abstract class AccessTokenModule {

    @Binds
    internal abstract fun bindAccessTokenService(impl: AccessTokenServiceImpl): AccessTokenService
}
