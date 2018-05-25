package lu.aqu.projper.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lu.aqu.projper.api.endpoint.ProjectService;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.mvp.RxPresenter;
import lu.aqu.projper.ui.ActivityScope;

@ActivityScope
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    ProjectService projectService;

    private List<Project> projects = new ArrayList<>();

    @Inject
    public HomePresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewAdded(HomeContract.View view) {
        super.onViewAdded(view);

        projectService.list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(collectDispoable())
                .subscribe(projects -> {
                    view.showModel(projects);
                    this.projects = projects;
                }, throwable -> {
                    Log.e("Home", "project lookup failed", throwable);
                    view.showMessage("project lookup failed");
                });
    }

    @Override
    public void onProjectClicked(Project project) {
        getView().showProject(project);
    }

    @Override
    public void onTagClicked(String tag) {
        if (projects != null) {
            final List<Project> filtered = new ArrayList<>();

            for (Project project : projects) {
                if (project.getTags().contains(tag)) {
                    filtered.add(project);
                }
            }

            getView().showModel(filtered);
        }
    }
}
