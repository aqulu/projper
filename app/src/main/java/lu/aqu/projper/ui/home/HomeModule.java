package lu.aqu.projper.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import lu.aqu.projper.R;
import lu.aqu.projper.ui.component.SpacerItemDecoration;

@Module
public abstract class HomeModule {

    @Provides
    static LinearLayoutManager linearLayoutManager(HomeActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    static SpacerItemDecoration spacerItemDecoration(HomeActivity activity) {
        return new SpacerItemDecoration(activity, SpacerItemDecoration.VERTICAL, R.dimen.item_spacing);
    }
}
