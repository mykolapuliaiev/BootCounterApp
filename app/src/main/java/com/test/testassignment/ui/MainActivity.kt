package com.test.testassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.test.testassignment.R
import com.test.testassignment.models.BootEvent

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            "${it.index + 1} - ${it.value}"
        }
    }
}