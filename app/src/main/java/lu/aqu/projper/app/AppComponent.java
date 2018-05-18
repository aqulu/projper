package lu.aqu.projper.app;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import lu.aqu.projper.ProjperApplication;
import lu.aqu.projper.api.ApiModule;
import lu.aqu.projper.ui.AppBindingModule;

@Singleton
@Component(modules = {
        ApiModule.class,
        AppBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(ProjperApplication application);

}
