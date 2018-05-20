package lu.aqu.projper.ui.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private final List<Project> projects = new ArrayList<>();

    @Inject
    public ProjectAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Project project = projects.get(position);
        holder.dataBinding.setProject(project);
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
