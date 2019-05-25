package lu.aqu.projper.project.details

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import lu.aqu.projper.project.BR
import lu.aqu.projper.project.R
import lu.aqu.projper.project.databinding.ViewHolderFeatureBinding
import lu.aqu.projper.support.DataBindingViewHolder

class FeatureAdapter : ListAdapter<String, FeatureAdapter.ViewHolder>(itemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(parent: ViewGroup) : DataBindingViewHolder<String, ViewHolderFeatureBinding>(
        parent,
        R.layout.view_holder_feature,
        BR.text
    )

    companion object {
        private val itemDiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}
