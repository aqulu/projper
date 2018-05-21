package lu.aqu.projper.mvp;

public interface BasePresenter<T extends BaseView> {

    T getView();

    void onViewAdded(T view);

    void onViewRemoved();

}
