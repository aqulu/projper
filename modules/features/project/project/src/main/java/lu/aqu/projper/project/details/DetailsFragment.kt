package lu.aqu.projper.project.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import lu.aqu.core.support.Resource
import lu.aqu.projper.project.R
import lu.aqu.projper.project.databinding.FragmentDetailsBinding
import lu.aqu.projper.project.details.di.DaggerDetailsComponent
import lu.aqu.projper.project.domain.Project
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: DetailsViewModel.Factory
    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: FragmentDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerDetailsComponent.builder()
            .projectId(Project.Id(args.id))
            .build()
            .inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentDetailsBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.project.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.project = it.data
                }

                is Resource.Error -> {
                    Log.w("Details", it.throwable)
                    Toast.makeText(context, R.string.error_data_fetching, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
