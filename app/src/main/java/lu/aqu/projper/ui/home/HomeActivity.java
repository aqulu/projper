package lu.aqu.projper.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ActivityHomeBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseActivity;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectsAdapter;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsBottomSheet;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {

    private ActivityHomeBinding binding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    SpacerItemDecoration itemDecoration;

    @Inject
    ProjectsAdapter projectsAdapter;

    @Inject
    ProjectDetailsBottomSheet bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showModel(List<Project> projects) {
        binding.projects.setAdapter(projectsAdapter);
        binding.projects.setLayoutManager(linearLayoutManager);
        binding.projects.addItemDecoration(itemDecoration);
        projectsAdapter.setProjects(projects);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(binding.container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProject(Project project) {
        bottomSheet.setProjectArgument(project);
        bottomSheet.show(getSupportFragmentManager(), "bottomsheet");
    }
}
