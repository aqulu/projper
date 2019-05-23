package lu.aqu.projper.support

import androidx.recyclerview.widget.DiffUtil
import lu.aqu.core.ddd.Entity

class EntityDiffCallback<T : Entity<*>> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
