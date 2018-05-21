package lu.aqu.projper.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import lu.aqu.projper.R;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectsAdapter;

@Module
public abstract class HomeModule {

    @Provides
    static HomeContract.Presenter provideHomePresenter(HomePresenter presenter) {
        return presenter;
    }

    @Provides
    static LinearLayoutManager linearLayoutManager(HomeActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    static SpacerItemDecoration spacerItemDecoration(HomeActivity activity) {
        return new SpacerItemDecoration(activity, SpacerItemDecoration.VERTICAL, R.dimen.item_spacing);
    }

    @Provides
    static ProjectsAdapter.OnClickCallback projectOnClickListener(HomeContract.Presenter presenter) {
        return presenter::onProjectClicked;
    }
}