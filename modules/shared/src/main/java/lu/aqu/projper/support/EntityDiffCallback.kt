package lu.aqu.projper.support

import androidx.recyclerview.widget.DiffUtil
import lu.aqu.core.ddd.Entity

/**
 * DiffUtil.ItemCallback implementation for Entity objects
 *
 * compares two entities [Entity.id] to check whether they represent the same item
 * performs an equality check, to determine if their content is the same
 */
class EntityDiffCallback<T : Entity<*>> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
