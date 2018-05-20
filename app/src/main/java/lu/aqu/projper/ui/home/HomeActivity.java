package lu.aqu.projper.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ActivityHomeBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseActivity;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectAdapter;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    private ActivityHomeBinding binding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    SpacerItemDecoration itemDecoration;

    @Inject
    ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showModel(List<Project> projects) {
        binding.projects.setAdapter(projectAdapter);
        binding.projects.setLayoutManager(linearLayoutManager);
        binding.projects.addItemDecoration(itemDecoration);
        projectAdapter.setProjects(projects);
    }
}
