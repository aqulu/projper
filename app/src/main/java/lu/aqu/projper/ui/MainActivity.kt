package lu.aqu.projper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import lu.aqu.core.util.Constants.AUTH_VIEW_MODEL
import lu.aqu.projper.R
import lu.aqu.projper.auth.login.LoginComponent
import lu.aqu.projper.di.componentHolder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = componentHolder
            .getComponent(LoginComponent::class)
            .loginViewModelFactory()

        // instantiate AuthViewModel for reuse within fragments
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(AUTH_VIEW_MODEL, AuthViewModel::class.java)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.navigation_fragment) as? NavHostFragment ?: return

        with(navHost.navController) {
            bottomNavigation.setupWithNavController(this)
            setupActionBarWithNavController(this, AppBarConfiguration(bottomNavigation.menu))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_fragment).navigateUp() ||
                super.onSupportNavigateUp()
    }
}
