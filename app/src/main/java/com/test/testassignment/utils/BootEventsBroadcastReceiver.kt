package com.test.testassignment.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.test.testassignment.models.BootEvent
import com.test.testassignment.repository.BootEventsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class BootEventsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: BootEventsRepository

    @Inject
    lateinit var workManager: WorkManager

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val currentMillis = System.currentTimeMillis()
                repository.addBootCompletedEvent(BootEvent(timestamp = currentMillis))
            }

            enqueueWorker()
        }
    }

    private fun enqueueWorker() {
        val workRequest =
            PeriodicWorkRequestBuilder<BootEventsWorker>(WORKER_INTERVAL, TimeUnit.MILLISECONDS)
                .setInputData(Data.Builder().build())
                .setConstraints(createWorkerConstraints())
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        workManager.enqueueUniquePeriodicWork(
            BootEventsWorker.NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun createWorkerConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresStorageNotLow(false)
        .setRequiresCharging(false)
        .build()

    companion object {
        private const val WORKER_INTERVAL = 15000L
    }
}