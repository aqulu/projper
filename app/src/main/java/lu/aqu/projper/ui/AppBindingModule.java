package lu.aqu.projper.ui;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import lu.aqu.projper.ui.home.HomeActivity;
import lu.aqu.projper.ui.home.HomeModule;

@Module
public abstract class AppBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract HomeActivity homeActivity();

}
