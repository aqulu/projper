package lu.aqu.projper.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lu.aqu.projper.R;
import lu.aqu.projper.api.endpoint.ProjectService;
import lu.aqu.projper.model.Project;

public class HomeActivity extends AppCompatActivity {

    @Inject
    ProjectService projectService;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Disposable disposable = projectService.list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(projects -> {
                    for (Project project : projects) {
                        Log.d("home", project.getName());
                    }
                }, throwable -> {
                    Log.d("home", "a-oh");
                });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
