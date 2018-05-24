package lu.aqu.projper.mvp;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;

public class RxPresenter<T extends BaseView> extends BasePresenterImpl<T> {

    private CompositeDisposable disposer = new CompositeDisposable();

    protected <D> ObservableTransformer<D, D> collectDispoable() {
        return upstream -> upstream.doOnSubscribe(disposable -> disposer.add(disposable));
    }

    @Override
    public void onViewRemoved() {
        disposer.clear();
        super.onViewRemoved();
    }
}
