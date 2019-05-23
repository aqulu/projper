package lu.aqu.projper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import lu.aqu.projper.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.navigation_fragment) as? NavHostFragment ?: return
        setupActionBarWithNavController(navHost.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_fragment).navigateUp() ||
                super.onSupportNavigateUp()
    }
}
