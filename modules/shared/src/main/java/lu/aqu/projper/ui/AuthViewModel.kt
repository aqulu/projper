package lu.aqu.projper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel to share authentication state over multiple modules
 */
abstract class AuthViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }

    abstract val authenticationState: LiveData<AuthenticationState>
}
