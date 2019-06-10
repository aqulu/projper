package lu.aqu.projper.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import lu.aqu.core.support.Resource
import lu.aqu.projper.auth.R
import lu.aqu.projper.auth.databinding.FragmentLoginBinding
import lu.aqu.projper.auth.login.di.LoginComponent
import lu.aqu.projper.di.componentHolder
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModel.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.getComponent(LoginComponent::class).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentLoginBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResult.observe(this, Observer {
            binding.isLoading = it is Resource.Loading
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.login_greeting, it.data.email),
                        Toast.LENGTH_LONG
                    ).show()
                    findNavController().popBackStack()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), R.string.login_error, Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.apply {
            login.setOnClickListener {
                viewModel.login(email.text.toString(), password.text.toString())
            }
        }
    }
}
