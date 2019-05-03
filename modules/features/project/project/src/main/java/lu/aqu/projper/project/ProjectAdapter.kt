package lu.aqu.projper.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lu.aqu.projper.project.databinding.ViewHolderProjectBinding
import lu.aqu.projper.project.domain.Project

class ProjectAdapter : ListAdapter<Project, ProjectAdapter.ViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: ViewHolderProjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun create(parent: ViewGroup): ViewHolder {
                val binding: ViewHolderProjectBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.view_holder_project,
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }


        fun bind(project: Project) {
            binding.project = project
        }
    }

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean =
                oldItem == newItem
        }
    }
}
