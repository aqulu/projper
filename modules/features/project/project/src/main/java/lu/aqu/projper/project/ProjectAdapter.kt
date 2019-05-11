package lu.aqu.projper.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lu.aqu.projper.project.databinding.ViewHolderProjectBinding
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.support.EntityDiffCallback

class ProjectAdapter : ListAdapter<Project, ProjectAdapter.ViewHolder>(EntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: ViewHolderProjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup) : this(
            ViewHolderProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


        fun bind(project: Project) {
            binding.project = project
        }
    }
}
