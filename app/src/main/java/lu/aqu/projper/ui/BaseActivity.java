package lu.aqu.projper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import lu.aqu.projper.mvp.BaseView;
import lu.aqu.projper.mvp.Presenter;

public abstract class BaseActivity<T extends Presenter> extends AppCompatActivity implements BaseView {

    @Inject
    T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        presenter.onViewAdded(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onViewRemoved();
        super.onDestroy();
    }
}
