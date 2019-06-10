package lu.aqu.projper.project.overview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_overview.projectRecyclerView
import lu.aqu.core.support.Resource
import lu.aqu.projper.di.componentHolder
import lu.aqu.projper.project.ProjectComponent
import lu.aqu.projper.project.R
import lu.aqu.projper.project.common.ProjectAdapter
import lu.aqu.projper.project.details.DetailsFragmentArgs
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: OverviewViewModel.Factory

    private val viewModel: OverviewViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.getComponent(ProjectComponent::class).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProjectAdapter {
            findNavController().navigate(
                R.id.detailsDest,
                DetailsFragmentArgs(it.name, it.id.value).toBundle()
            )
        }
        projectRecyclerView.adapter = adapter

        viewModel.projects.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    adapter.submitList(it.data)
                }

                is Resource.Error -> {
                    Log.w("Overview", it.throwable)
                    Toast.makeText(context, R.string.error_data_fetching, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
