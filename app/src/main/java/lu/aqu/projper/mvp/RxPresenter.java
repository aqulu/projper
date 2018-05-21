package lu.aqu.projper.mvp;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    private CompositeDisposable disposer = new CompositeDisposable();
    private T view;

    protected <D> ObservableTransformer<D, D> collectDispoable() {
        return upstream -> upstream.doOnSubscribe(disposable -> disposer.add(disposable));
    }

    @Override
    public T getView() {
        return view;
    }

    @Override
    public void onViewAdded(T view) {
        this.view = view;
    }

    @Override
    public void onViewRemoved() {
        disposer.clear();
        view = null;
    }
}
