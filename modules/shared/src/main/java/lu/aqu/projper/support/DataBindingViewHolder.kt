package lu.aqu.projper.support

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingViewHolder<DataT, BindingT : ViewDataBinding> private constructor(
    protected val binding: BindingT,
    private val variableId: Int
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * @param parent ViewGroup this ViewHolder belongs to
     * @param layoutId the view to be inflated
     * @param variableId the BR id of the variable to be set
     */
    constructor(parent: ViewGroup, @LayoutRes layoutId: Int, variableId: Int) : this(
        DataBindingUtil.inflate<BindingT>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        ),
        variableId
    )

    /**
     * binds the supplied value to the ViewDataBinding's variable with id [variableId]
     */
    fun bind(value: DataT) {
        binding.setVariable(variableId, value)
    }
}
