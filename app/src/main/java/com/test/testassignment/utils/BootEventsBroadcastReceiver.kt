package com.test.testassignment.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.test.testassignment.models.BootEvent
import com.test.testassignment.repository.BootEventsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootEventsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: BootEventsRepository
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val currentMillis = System.currentTimeMillis()
                repository.addBootCompletedEvent(BootEvent(timestamp = currentMillis))
            }
            // todo: show notification
        }
    }
}