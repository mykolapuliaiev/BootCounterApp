package com.test.testassignment.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BootEventsBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == Intent.ACTION_BOOT_COMPLETED) {
            // todo: track event
            // todo: show notification
        }
    }
}