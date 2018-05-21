package lu.aqu.projper.ui.home;

import java.util.List;

import lu.aqu.projper.model.Project;
import lu.aqu.projper.mvp.BasePresenter;
import lu.aqu.projper.mvp.BaseView;

public interface HomeContract {

    interface View extends BaseView {
        void showModel(List<Project> model);

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void onProjectClicked(Project project);

        void onTagClicked(String tag);
    }

}
