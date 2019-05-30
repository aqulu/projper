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
import lu.aqu.projper.ui.OnTagClickListener
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactoryProvider: DetailsViewModelFactory.Provider
    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: FragmentDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerDetailsComponent.create().inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactoryProvider.provide(Project.Id(args.id)))
            .get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentDetailsBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onTagClickListener = object : OnTagClickListener {
            override fun onClick(tag: String) {
                // TODO search for tag
                Log.d("Details", "$tag was clicked")
            }
        }

        val featureAdapter = FeatureAdapter().also {
            binding.featuresRecyclerView.adapter = it
        }

        viewModel.project.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.project = it.data
                    featureAdapter.submitList(it.data.features)
                }

                is Resource.Error -> {
                    Log.w("Details", it.throwable)
                    Toast.makeText(context, R.string.error_data_fetching, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
