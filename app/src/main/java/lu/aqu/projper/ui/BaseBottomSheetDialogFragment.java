package lu.aqu.projper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;

import javax.inject.Inject;

import lu.aqu.projper.mvp.BasePresenter;
import lu.aqu.projper.mvp.BaseView;

public abstract class BaseBottomSheetDialogFragment<T extends BasePresenter> extends BottomSheetDialogFragment implements BaseView {

    @Inject
    T presenter;

    public T getPresenter() {
        return presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onViewAdded(this);
    }

    @Override
    public void onDetach() {
        presenter.onViewRemoved();
        super.onDetach();
    }

}
