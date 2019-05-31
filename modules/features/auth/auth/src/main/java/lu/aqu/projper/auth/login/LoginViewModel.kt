package lu.aqu.projper.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lu.aqu.projper.auth.usecase.LoginUseCase
import javax.inject.Inject

class LoginViewModel private constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // TODO implement login

    class Factory @Inject constructor(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(loginUseCase) as T
        }
    }
}
