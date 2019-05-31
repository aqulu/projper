package lu.aqu.projper.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lu.aqu.projper.auth.databinding.FragmentLoginBinding
import lu.aqu.projper.auth.login.di.LoginComponent
import lu.aqu.projper.di.componentHolder
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModel.Factory
    private lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.getComponent(LoginComponent::class).inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentLoginBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
}
