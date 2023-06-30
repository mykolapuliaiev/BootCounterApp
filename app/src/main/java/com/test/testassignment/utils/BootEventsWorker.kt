package com.test.testassignment.utils

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.test.testassignment.R
import com.test.testassignment.repository.BootEventsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class BootEventsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: BootEventsRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val bootEvents = repository.getBootCompletedEvents()
        val text = when (bootEvents.size) {
            0 -> applicationContext.getString(R.string.no_boots_text)
            1 -> applicationContext.getString(
                R.string.one_boot_text,
                bootEvents.first().timestamp.toString()
            )

            else -> {
                val lastEvent = bootEvents.last()
                val twoLastEventsDelta =
                    lastEvent.timestamp - bootEvents[bootEvents.lastIndex - 1].timestamp
                applicationContext.getString(
                    R.string.multiple_boots_text,
                    twoLastEventsDelta.toString()
                )
            }
        }

        NotificationsUtil(applicationContext).displayNotification(text)

        return Result.success()
    }

    companion object {
        const val NAME = "Boot events worker"
    }
}