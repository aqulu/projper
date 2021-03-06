package lu.aqu.projper.project.bookmarks

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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_bookmarks.bookmarksRecyclerView
import lu.aqu.core.support.Resource
import lu.aqu.core.util.Constants
import lu.aqu.projper.di.componentHolder
import lu.aqu.projper.project.ProjectComponent
import lu.aqu.projper.project.R
import lu.aqu.projper.project.common.ProjectAdapter
import lu.aqu.projper.project.details.DetailsFragmentArgs
import lu.aqu.projper.ui.AuthViewModel
import javax.inject.Inject

class BookmarksFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: BookmarkViewModel.Factory
    private val viewModel: BookmarkViewModel by viewModels { viewModelFactory }

    /**
     * ViewModel holding shared authentication state
     */
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProviders
            .of(requireActivity())
            .get(Constants.AUTH_VIEW_MODEL, AuthViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.getComponent(ProjectComponent::class).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.authenticationState.observe(this, Observer {
            if (it == AuthViewModel.AuthenticationState.UNAUTHENTICATED) {
                findNavController().navigate(R.id.loginDest)
            }
        })

        val adapter = ProjectAdapter {
            findNavController().navigate(
                R.id.detailsDest,
                DetailsFragmentArgs(it.name, it.id.value).toBundle()
            )
        }
        bookmarksRecyclerView.adapter = adapter

        viewModel.load()
        viewModel.bookmarks.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    adapter.submitList(it.data)
                }

                is Resource.Error -> {
                    Log.w("Bookmarks", it.throwable)
                    Toast.makeText(context, R.string.error_data_fetching, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
