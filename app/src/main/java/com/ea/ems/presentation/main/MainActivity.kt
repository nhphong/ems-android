package com.ea.ems.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.ea.ems.R
import com.ea.ems.core.util.setVisible
import com.ea.ems.core.util.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make the status bar transparent
        window.apply {
            clearFlags(FLAG_TRANSLUCENT_STATUS)
            addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
        bindViewModel()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    private fun bindViewModel() {
        viewModel.run {
            showBottomBar.observe {
                bottomNavView.setVisible(it)
            }
            moveToHomeScreen.observe {
                currentNavController?.value?.setGraph(R.navigation.nav_graph_home)
            }
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.nav_graph_home,
            R.navigation.nav_graph_order_history,
            R.navigation.nav_graph_schedule,
            R.navigation.nav_graph_info
        )
        currentNavController = bottomNavView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )

        currentNavController?.value?.setGraph(R.navigation.nav_graph_onboarding)
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(this@MainActivity, { block(it) })
    }
}
