package lu.aqu.projper.mvp;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T view;

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
        view = null;
    }
}
