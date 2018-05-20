package lu.aqu.projper.ui.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ProjectCardBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.component.SpacerItemDecoration;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private final List<Project> projects = new ArrayList<>();
    private Context context = null;

    @Inject
    public ProjectsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.project_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Project project = projects.get(position);
        if (holder.dataBinding != null) {
            holder.dataBinding.setProject(project);
            holder.dataBinding.tags.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.dataBinding.tags.addItemDecoration(new SpacerItemDecoration(context, SpacerItemDecoration.HORIZONTAL, R.dimen.space_sm));
            holder.dataBinding.tags.setAdapter(new TagsAdapter(project.getTags()));
        }
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    /**
     * clears the previously held data and adds the supplied data
     *
     * @param projects projects to display
     */
    public void setProjects(List<Project> projects) {
        if (projects != null) {
            this.projects.clear();
            this.projects.addAll(projects);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ProjectCardBinding dataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
