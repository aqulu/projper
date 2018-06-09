package lu.aqu.projper.ui.home.dialog;

import javax.inject.Inject;

import lu.aqu.projper.model.Project;
import lu.aqu.projper.mvp.BasePresenterImpl;

public class ProjectDetailsPresenter extends BasePresenterImpl<ProjectDetailsContract.View> implements ProjectDetailsContract.Presenter {

    private Project projectCache;

    @Inject
    public ProjectDetailsPresenter() {

    }

    @Override
    public void onViewAdded(ProjectDetailsContract.View view) {
        super.onViewAdded(view);
        projectCache = getView().getProjectArgument();
        getView().showModel(projectCache);
    }
}
