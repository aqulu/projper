package lu.aqu.projper.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.util.List;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ActivityHomeBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseActivity;
import lu.aqu.projper.ui.home.component.ProjectAdapter;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showModel(List<Project> projects) {
        binding.projects.setAdapter(new ProjectAdapter(projects));
    }
}
