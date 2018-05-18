package lu.aqu.projper.app;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import lu.aqu.projper.ProjperApplication;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(ProjperApplication application);

}
