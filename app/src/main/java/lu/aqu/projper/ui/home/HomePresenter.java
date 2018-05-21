package lu.aqu.projper.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

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
                    for (Project project : projects) {
                        Log.d("home", project.getName());
                    }
                }, throwable -> Log.d("home", "a-oh"));
    }

    @Override
    public void onProjectClicked(Project project) {
        getView().showMessage(project.getName() + " has been clicked");
    }
}
