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
import lu.aqu.projper.databinding.FeatureItemBinding;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.ViewHolder> {

    private final List<String> features = new ArrayList<>();

    @Inject
    public FeaturesAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.dataBinding != null) {
            holder.dataBinding.setFeature(features.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public void setFeatures(List<String> features) {
        this.features.clear();
        if (features != null) {
            this.features.addAll(features);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FeatureItemBinding dataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
        }
    }
}
