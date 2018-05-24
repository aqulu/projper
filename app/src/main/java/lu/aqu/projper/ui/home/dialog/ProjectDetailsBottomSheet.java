package lu.aqu.projper.ui.home.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.BottomSheetProjectBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.BaseBottomSheetDialogFragment;
import lu.aqu.projper.ui.component.SpacerItemDecoration;
import lu.aqu.projper.ui.home.adapter.FeaturesAdapter;
import lu.aqu.projper.ui.home.adapter.TagsAdapter;

public class ProjectDetailsBottomSheet extends BaseBottomSheetDialogFragment<ProjectDetailsContract.Presenter> implements ProjectDetailsContract.View {

    private static final String ARG_PROJECT = "project";

    private BottomSheetProjectBinding dataBinding;

    @Inject
    TagsAdapter tagsAdapter;

    @Inject
    FeaturesAdapter featuresAdapter;

    @Inject
    public ProjectDetailsBottomSheet() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bottom_sheet_project, container, false);
        dataBinding = DataBindingUtil.bind(view);

        if (dataBinding != null && getContext() != null) {
            dataBinding.features.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            dataBinding.features.addItemDecoration(new SpacerItemDecoration(getContext(), SpacerItemDecoration.VERTICAL, R.dimen.space_sm));
            dataBinding.features.setAdapter(featuresAdapter);

            dataBinding.tags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            dataBinding.tags.addItemDecoration(new SpacerItemDecoration(getContext(), SpacerItemDecoration.HORIZONTAL, R.dimen.space_sm));
            dataBinding.tags.setAdapter(tagsAdapter);
        }

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
        featuresAdapter.setFeatures(project.getFeatures());
        tagsAdapter.setTags(project.getTags());
    }
}
