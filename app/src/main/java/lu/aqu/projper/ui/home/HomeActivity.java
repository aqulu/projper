package lu.aqu.projper.ui.home;

import android.os.Bundle;

import lu.aqu.projper.R;
import lu.aqu.projper.ui.BaseActivity;

public class HomeActivity extends BaseActivity<HomePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
