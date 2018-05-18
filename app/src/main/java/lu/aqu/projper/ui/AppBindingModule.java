package lu.aqu.projper.ui;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import lu.aqu.projper.ui.home.HomeActivity;

@Module
public abstract class AppBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract HomeActivity homeActivity();

}
