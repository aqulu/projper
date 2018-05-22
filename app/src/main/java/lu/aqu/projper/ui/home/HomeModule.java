package lu.aqu.projper.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import lu.aqu.projper.R;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectsAdapter;

@Module
public abstract class HomeModule {

    @Provides
    static HomeContract.Presenter homePresenter(HomePresenter presenter) {
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
    static ProjectsAdapter.ProjectClickCallback projectOnClickListener(HomeContract.Presenter presenter) {
        return new ProjectsAdapter.ProjectClickCallback() {
            @Override
            public void onClick(Project project) {
                presenter.onProjectClicked(project);
            }

            @Override
            public void onClick(String tag) {
                presenter.onTagClicked(tag);
            }
        };
    }
}
