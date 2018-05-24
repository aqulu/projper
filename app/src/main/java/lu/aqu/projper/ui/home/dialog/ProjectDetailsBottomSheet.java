package lu.aqu.projper.ui.home.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.BottomSheetProjectBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseBottomSheetDialogFragment;

public class ProjectDetailsBottomSheet extends BaseBottomSheetDialogFragment<ProjectDetailsContract.Presenter> implements ProjectDetailsContract.View {

    private static final String ARG_PROJECT = "project";

    private BottomSheetProjectBinding dataBinding;

    @Inject
    public ProjectDetailsBottomSheet() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bottom_sheet_project, container, false);
        dataBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public Project getProjectArgument() {
        return getArguments() != null ? getArguments().getParcelable(ARG_PROJECT) : null;
    }

    public void setProjectArgument(Project project) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PROJECT, project);
        setArguments(args);
    }

    @Override
    public void showModel(Project project) {
        dataBinding.setProject(project);
    }
}
