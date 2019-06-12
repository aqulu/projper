package lu.aqu.projper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class AuthViewModel : ViewModel() {
    
    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }

    abstract val authenticationState: LiveData<AuthenticationState>
}
