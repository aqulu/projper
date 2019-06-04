package lu.aqu.projper.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import lu.aqu.core.util.Constants
import lu.aqu.projper.auth.hostservice.AccessTokenModule
import lu.aqu.projper.auth.hostservice.AccessTokenService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [AccessTokenModule::class])
internal class ApiModule {

    @Provides
    fun provideAuthInterceptor(accessTokenService: AccessTokenService): Interceptor =
        Interceptor { chain ->
            val accessToken = accessTokenService.getAccessToken()

            val request = chain.request()
                .newBuilder()
                .apply {
                    if (accessToken != null) {
                        addHeader(Constants.Api.AUTH_HEADER, "Bearer $accessToken")
                    }
                }
                .build()

            chain.proceed(request)
        }

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(Constants.Api.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}
