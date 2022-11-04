package com.example.bottomnavigationactivity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigationactivity.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


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

    if (!checkPermission()) {
      requestPermission();
      return;
    }
  }

  private fun checkPermission(): Boolean {
    val result = ContextCompat.checkSelfPermission(
      this@MainActivity,
      Manifest.permission.READ_EXTERNAL_STORAGE
    )
    return result == PackageManager.PERMISSION_GRANTED
  }

  private fun requestPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(
        this@MainActivity,
        Manifest.permission.READ_EXTERNAL_STORAGE
      )
    ) {
      Toast.makeText(
        this@MainActivity,
        "READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS",
        Toast.LENGTH_SHORT
      ).show()
    } else ActivityCompat.requestPermissions(
      this@MainActivity,
      arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
      123
    )
  }

  override fun onResume() {
    super.onResume()
    /*
    if (recyclerView != null) {
      recyclerView.setAdapter(MusicListAdapter(songsList, applicationContext))
    }
     */
  }
}