package lu.aqu.projper.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import lu.aqu.core.coroutine.CoroutineUseCaseWithParameterAbs
import lu.aqu.core.support.Resource
import lu.aqu.projper.auth.domain.User
import lu.aqu.projper.auth.usecase.LoginQuery
import lu.aqu.projper.auth.usecase.LoginUseCase
import lu.aqu.projper.ui.AuthViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times

class LoginViewModelTest {
    @get:Rule
    val schedulers = InstantTaskExecutorRule()

    private lateinit var loginObserver: Observer<Resource<User>>
    private lateinit var authenticationStateObserver: Observer<AuthViewModel.AuthenticationState>

    @ExperimentalCoroutinesApi
    @Before
    @Suppress("UNCHECKED_CAST")
    fun before() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        loginObserver = mock(Observer::class.java) as Observer<Resource<User>>
        authenticationStateObserver = mock(Observer::class.java) as Observer<AuthViewModel.AuthenticationState>
    }

    @Test
    fun testLogin_Success() {
        val loginViewModel = LoginViewModel(
            object : CoroutineUseCaseWithParameterAbs<LoginQuery, User>(
                Dispatchers.Main,
                Dispatchers.Default
            ), LoginUseCase {
                override suspend fun doInBackground(param: LoginQuery): User = Results.USER
            }
        )

        loginViewModel.authenticationState.observeForever(authenticationStateObserver)
        loginViewModel.loginResult.observeForever(loginObserver)

        loginViewModel.login(Params.EMAIL, Params.PASSWORD)

        @Suppress("UNCHECKED_CAST") val argumentCaptor: ArgumentCaptor<Resource<User>> =
            ArgumentCaptor.forClass(Resource::class.java) as ArgumentCaptor<Resource<User>>

        // Loading and Success emission
        verify(loginObserver, Times(2)).onChanged(argumentCaptor.capture())
        verify(authenticationStateObserver).onChanged(AuthViewModel.AuthenticationState.AUTHENTICATED)

        Assert.assertTrue(argumentCaptor.value is Resource.Success)
        Assert.assertEquals(Results.USER, (argumentCaptor.value as Resource.Success<User>).data)
    }

    @Test
    fun testLogin_Error() {
        val loginViewModel = LoginViewModel(
            object : CoroutineUseCaseWithParameterAbs<LoginQuery, User>(
                Dispatchers.Main,
                Dispatchers.Default
            ), LoginUseCase {
                override suspend fun doInBackground(param: LoginQuery): User =
                    throw Exception("test")
            }
        )

        loginViewModel.authenticationState.observeForever(authenticationStateObserver)
        loginViewModel.loginResult.observeForever(loginObserver)

        loginViewModel.login(Params.EMAIL, Params.PASSWORD)
        @Suppress("UNCHECKED_CAST") val argumentCaptor: ArgumentCaptor<Resource<User>> =
            ArgumentCaptor.forClass(Resource::class.java) as ArgumentCaptor<Resource<User>>

        // FIXME urgh... find a clean way to handle in LiveData encapsulated coroutines execution
        Thread.sleep(300)

        // Loading and Error emission
        verify(loginObserver, Times(2)).onChanged(argumentCaptor.capture())
        // initial, Loading and Error emission of UNAUTHENTICATED
        verify(authenticationStateObserver, Times(3))
            .onChanged(AuthViewModel.AuthenticationState.UNAUTHENTICATED)

        Assert.assertTrue(argumentCaptor.value is Resource.Error)
    }

    private object Params {
        const val EMAIL = "chuck@norris.world"
        const val PASSWORD = "chucknorris1"
    }

    private object Results {
        val USER = User(User.Id(0L), Params.EMAIL)
    }
}
