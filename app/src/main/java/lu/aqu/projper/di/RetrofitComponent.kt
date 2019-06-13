package lu.aqu.projper.di

import dagger.BindsInstance
import dagger.Component
import lu.aqu.projper.auth.hostservice.AccessTokenService
import retrofit2.Retrofit

@Component(
    modules = [ApiModule::class]
)
interface RetrofitComponent {

    /**
     * provides a retrofit instance with an authentication interceptor
     */
    fun retrofit(): Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun accessTokenService(accessTokenService: AccessTokenService): Builder

        fun build(): RetrofitComponent
    }
}
