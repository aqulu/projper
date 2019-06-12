package lu.aqu.projper.auth.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import lu.aqu.core.coroutine.ControlledRunner
import lu.aqu.core.support.Resource
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.usecase.LoginQuery
import lu.aqu.projper.auth.usecase.LoginUseCase
import lu.aqu.projper.ui.AuthViewModel
import javax.inject.Inject

class LoginViewModel private constructor(
    loginUseCase: LoginUseCase
) : AuthViewModel() {

    override val authenticationState = MutableLiveData<AuthenticationState>(AuthenticationState.UNAUTHENTICATED)

    /**
     * login usecase chain emitting results to [mutableLoginResult]
     */
    private val loginAction = loginUseCase
        .onLoading {
            authenticationState.value = AuthenticationState.UNAUTHENTICATED
            mutableLoginResult.value = Resource.Loading
        }
        .onError {
            authenticationState.value = AuthenticationState.UNAUTHENTICATED
            mutableLoginResult.value = Resource.Error(it)
        }
        .onResult {
            authenticationState.value = AuthenticationState.AUTHENTICATED
            mutableLoginResult.value = Resource.Success(it)
        }

    /**
     * concurrency helper to avoid multiple login at once
     */
    private val controlledRunner = ControlledRunner<Unit>()

    private val mutableLoginResult = MutableLiveData<Resource<User>>()
    val loginResult = mutableLoginResult

    fun login(email: String, password: String): LiveData<Resource<User>> {
        viewModelScope.launch {
            controlledRunner.cancelPreviousThenRun {
                loginAction.invoke(LoginQuery(email, password))
            }
        }
        return loginResult
    }

    class Factory @Inject constructor(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(loginUseCase) as T
        }
    }
}
