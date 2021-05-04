package com.mzheng9.vanillavinyl

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {



    private lateinit var navHostFragment: NavHostFragment
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_VanillaVinyl)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
        if (savedInstanceState == null) {
            if (prefs.getBoolean(SHOW_MESSAGE_AT_START, false)) {
                welcomeAlert()
            }
        }
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {
                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.infoFragment -> getString(R.string.info_frag_textview)
                    R.id.recordsDisplayFragment -> getString(R.string.display_frag_textview)
                    R.id.dataEntryFragment -> getString(R.string.data_entry_frag_textview)
                    else -> getString(R.string.app_name)
                }
            }
        }
    }

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.menu_settings -> {
//                navHostFragment.navController.navigate(R.id.action_homeFragment_to_settingsFragment)
//                true
//            }
            R.id.settings_menuItem -> {
                navHostFragment.navController.navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }
            R.id.reset_menuItem -> {

                with(prefs.edit()) {
                    remove(SHOW_NOW_IMAGE)
                    remove(SHOW_MESSAGE_AT_START)
                    remove(EFFECT_SELECTION)
                    apply()
                }
                true
            }
            R.id.info_menuItem -> {
                navHostFragment.navController.navigate(R.id.action_homeFragment_to_infoFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun welcomeAlert() {
        val msg = resources.getString(R.string.message_body)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.welcome)
            setMessage(msg)
            setIcon(R.drawable.app_icon_1)
            setPositiveButton(R.string.ok, null)
            show()
        }
    }

    private fun isConnected(): Boolean {

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = cm.activeNetwork ?: return false
        val nwCapabilities = cm.getNetworkCapabilities(nw) ?: return false
        return when {
            nwCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            nwCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
    companion object {
        const val SHOW_MESSAGE_AT_START = "show_message_at_start"
        const val SHOW_NOW_IMAGE = "show_now_image"
        const val EFFECT_SELECTION = "effect_selection"
    }
}