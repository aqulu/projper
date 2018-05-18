package lu.aqu.projper.mvp;

public interface Presenter<T extends BaseView> {

    void onViewAdded(T view);

    void onViewRemoved();

}
