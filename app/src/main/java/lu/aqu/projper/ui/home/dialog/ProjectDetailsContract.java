package lu.aqu.projper.ui.home.dialog;

import lu.aqu.projper.model.Project;
import lu.aqu.projper.mvp.BasePresenter;
import lu.aqu.projper.mvp.BaseView;

public interface ProjectDetailsContract {

    interface View extends BaseView {
        Project getProjectArgument();

        void showModel(Project project);
    }

    interface Presenter extends BasePresenter<View> {
    }

}