package com.example.bottomnavigationactivity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigationactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  @RequiresApi(Build.VERSION_CODES.R)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navView: BottomNavigationView = binding.navView

    val navController = findNavController(R.id.nav_host_fragment_activity_main)

    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_my_music, R.id.navigation_playlist, R.id.navigation_favorites
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

    verifyPermissionsForAccessInFiles(this@MainActivity)
  }

  @RequiresApi(Build.VERSION_CODES.R)
  private fun verifyPermissionsForAccessInFiles (context: Context) {
    if (!Environment.isExternalStorageManager()
    ) {
      AlertDialog.Builder(context).setTitle("Acceso a archivos")
        .setMessage("La aplicación necesita acceso a algunos archivos")
        .setPositiveButton("Otorgar permisos") { dialogInterface, i ->
          startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
        }
        .setCancelable(false).show()
    } else {
      Toast.makeText(this, "tiene los permisos", Toast.LENGTH_SHORT).show()
    }
  }
}