package lu.aqu.projper.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ActivityHomeBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseActivity;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.ProjectAdapter;

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
        binding.projects.setLayoutManager(new LinearLayoutManager(this));
        binding.projects.addItemDecoration(new SpacerItemDecoration(this, SpacerItemDecoration.VERTICAL, R.dimen.item_spacing));
    }
}
