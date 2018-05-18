package lu.aqu.projper.ui.home;

import android.os.Bundle;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.api.endpoint.ProjectService;
import lu.aqu.projper.ui.BaseActivity;

public class HomeActivity extends BaseActivity<HomePresenter> {

    @Inject
    ProjectService projectService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
