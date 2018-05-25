package lu.aqu.projper.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ActivityHomeBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseActivity;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectsAdapter;
import lu.aqu.projper.ui.home.adapter.TagsAdapter;
import lu.aqu.projper.ui.home.dialog.ProjectDetailsBottomSheet;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {

    private ActivityHomeBinding binding;

    @Inject
    @Named("projectsLayoutManager")
    LinearLayoutManager projectLayoutManager;

    @Inject
    @Named("tagsLayoutManager")
    LinearLayoutManager tagsLayoutManager;

    @Inject
    @Named("projectsSpacer")
    SpacerItemDecoration projectsSpacer;

    @Inject
    @Named("tagsSpacer")
    SpacerItemDecoration tagsSpacer;

    @Inject
    ProjectsAdapter projectsAdapter;

    @Inject
    ProjectDetailsBottomSheet bottomSheet;

    @Inject
    @Named("filterTagsAdapter")
    TagsAdapter tagsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showFilterTags(List<String> tags) {
        if (binding.filterTags.getAdapter() == null) {
            binding.filterTags.setAdapter(tagsAdapter);
            binding.filterTags.addItemDecoration(tagsSpacer);
            binding.filterTags.setLayoutManager(tagsLayoutManager);
        }

        tagsAdapter.setTags(tags);
    }

    @Override
    public void showModel(List<Project> projects) {
        if (binding.projects.getAdapter() == null) {
            binding.projects.setAdapter(projectsAdapter);
            binding.projects.setLayoutManager(projectLayoutManager);
            binding.projects.addItemDecoration(projectsSpacer);
        }

        projectsAdapter.setProjects(projects);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProject(Project project) {
        bottomSheet.setProjectArgument(project);
        bottomSheet.show(getSupportFragmentManager(), "bottomsheet");
    }
}
