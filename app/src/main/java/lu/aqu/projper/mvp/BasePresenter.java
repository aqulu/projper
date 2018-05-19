package lu.aqu.projper.mvp;

public interface BasePresenter<T extends BaseView> {

    void onViewAdded(T view);

    void onViewRemoved();

}
