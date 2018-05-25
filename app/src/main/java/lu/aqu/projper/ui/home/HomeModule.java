package lu.aqu.projper.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Comparator;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import lu.aqu.projper.R;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectsAdapter;
import lu.aqu.projper.ui.home.adapter.TagsAdapter;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsBottomSheet;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsContract;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsModule;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsPresenter;

@Module
public abstract class HomeModule {

    @Provides
    static ProjectDetailsBottomSheet projectDetailsBottomSheet() {
        return new ProjectDetailsBottomSheet();
    }

    @ContributesAndroidInjector(modules = {ProjectDetailsModule.class})
    public abstract ProjectDetailsBottomSheet projectDetailsBottomSheetInjector();

    @Binds
    abstract HomeContract.Presenter homePresenter(HomePresenter presenter);

    @Binds
    abstract ProjectDetailsContract.Presenter projectDetailsPresenter(ProjectDetailsPresenter presenter);

    @Provides
    @Named("projectsLayoutManager")
    static LinearLayoutManager projectsLayoutManager(HomeActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @Named("projectsSpacer")
    static SpacerItemDecoration projectsSpacer(HomeActivity activity) {
        return new SpacerItemDecoration(activity, SpacerItemDecoration.VERTICAL, R.dimen.item_spacing);
    }

    @Provides
    @Named("tagsLayoutManager")
    static LinearLayoutManager tagsLayoutManager(HomeActivity activity) {
        return new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    @Named("tagsSpacer")
    static SpacerItemDecoration tagsSpacer(HomeActivity activity) {
        return new SpacerItemDecoration(activity, SpacerItemDecoration.HORIZONTAL, R.dimen.space_sm);
    }

    @Provides
    static Comparator<Project> projectComparator() {
        return (project, t1) -> Long.compare(project.getId(), t1.getId());
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

    @Provides
    @Named("filterTagsAdapter")
    static TagsAdapter filterTagsAdapter(HomeContract.Presenter presenter) {
        return new TagsAdapter(new ArrayList<>(), presenter::onFilterTagClicked);
    }
}
