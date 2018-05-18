package lu.aqu.projper.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lu.aqu.projper.api.endpoint.ProjectService;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {

    @Provides
    @Singleton
    public static ProjectService projectApi(Retrofit retrofit) {
        return retrofit.create(ProjectService.class);
    }

}
