package lu.aqu.projper.project.overview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import lu.aqu.projper.project.BR
import lu.aqu.projper.project.R
import lu.aqu.projper.project.databinding.ViewHolderProjectBinding
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.support.DataBindingViewHolder
import lu.aqu.projper.support.EntityDiffCallback

class ProjectAdapter(
    private val onClick: (Project) -> Unit
) : ListAdapter<Project, ProjectAdapter.ViewHolder>(EntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        parent: ViewGroup,
        onClick: (Project) -> Unit
    ) : DataBindingViewHolder<Project, ViewHolderProjectBinding>(
        parent,
        R.layout.view_holder_project,
        BR.project
    ) {

        init {
            binding.setOnClick {
                binding.project?.run(onClick)
            }
        }
    }
}
