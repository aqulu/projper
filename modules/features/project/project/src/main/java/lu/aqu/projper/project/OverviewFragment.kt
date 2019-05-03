package lu.aqu.projper.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_overview.*
import lu.aqu.projper.project.di.DaggerOverviewComponent
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: OverviewViewModel.Factory

    private lateinit var viewModel: OverviewViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerOverviewComponent.create().inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProjectAdapter()
        projectRecyclerView.adapter = adapter
        viewModel.projects.observe(this, Observer(adapter::submitList))
    }
}
