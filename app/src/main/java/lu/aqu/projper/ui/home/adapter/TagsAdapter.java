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
import lu.aqu.projper.databinding.TagItemBinding;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private final List<String> tags;
    private final TagClickCallback callback;

    @Inject
    public TagsAdapter() {
        this(new ArrayList<>(), null);
    }

    public TagsAdapter(List<String> tags, TagClickCallback callback) {
        this.tags = tags != null ? tags : new ArrayList<>();
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.dataBinding != null) {
            holder.dataBinding.setTag(tags.get(position));
            holder.dataBinding.setCallback(callback);
        }
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void setTags(List<String> tags) {
        if (tags != null) {
            this.tags.clear();
            this.tags.addAll(tags);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TagItemBinding dataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface TagClickCallback {
        void onClick(String tag);
    }
}
