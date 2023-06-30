package com.test.testassignment.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.test.testassignment.R
import com.test.testassignment.models.BootEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPermissionLauncher()
        requestNotificationPermission()

        viewModel.bootEventsLiveData.observe(this) {
            displayBootTimestamps(it)
        }
    }

    private fun displayBootTimestamps(timestamps: List<BootEvent>) {
        val textView = findViewById<TextView>(R.id.timestamps_tv)
        if (timestamps.isEmpty()) {
            textView.text = getString(R.string.no_boots_text)
            return
        }

        textView.text = timestamps.withIndex().joinToString(separator = "\n") {
            "${it.index + 1} - ${it.value.timestamp}"
        }
    }

    private fun initPermissionLauncher() {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    // notification permission granted
                } else {
                    // notification permission not granted
                }
            }
    }

    private fun requestNotificationPermission() {
        if (
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // ready to show notifications
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            Toast.makeText(
                this@MainActivity,
                "Please, allow notification permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}