package lu.aqu.projper.ui.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import lu.aqu.projper.R;
import lu.aqu.projper.databinding.ProjectCardBinding;
import lu.aqu.projper.model.Project;
import lu.aqu.projper.ui.component.SpacerItemDecoration;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private final ProjectClickCallback mCallback;
    private final Comparator<Project> mComparator;

    @Inject
    public ProjectsAdapter(ProjectClickCallback callback, Comparator<Project> comparator) {
        mCallback = callback;
        mComparator = comparator;
    }

    private final SortedList<Project> mProjects = new SortedList<>(Project.class, new SortedList.Callback<Project>() {

        @Override
        public int compare(Project o1, Project o2) {
            return mComparator.compare(o1, o2);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Project oldItem, Project newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Project item1, Project item2) {
            return item1.getId() == item2.getId();
        }
    });

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Project project = mProjects.get(position);
        if (holder.dataBinding != null) {
            holder.dataBinding.setProject(project);
            holder.dataBinding.setCallback(mCallback);
            holder.dataBinding.tags.setAdapter(new TagsAdapter(project.getTags(), mCallback));
        }
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    /**
     * clears the previously held data and adds the supplied data
     *
     * @param projects projects to display
     */
    public void setProjects(List<Project> projects) {
        mProjects.beginBatchedUpdates();

        mProjects.clear();
        if (projects != null) {
            mProjects.addAll(projects);
        }

        mProjects.endBatchedUpdates();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ProjectCardBinding dataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
            if (dataBinding != null) {
                dataBinding.tags.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                dataBinding.tags.addItemDecoration(new SpacerItemDecoration(itemView.getContext(), SpacerItemDecoration.HORIZONTAL, R.dimen.space_sm));
            }
        }
    }

    public interface ProjectClickCallback extends TagsAdapter.TagClickCallback {
        void onClick(Project project);
    }
}
