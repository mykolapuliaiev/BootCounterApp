package com.test.testassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.test.testassignment.db.dao.BootCompletedDao
import com.test.testassignment.models.BootEvent
import javax.inject.Inject

class BootEventsRepository @Inject constructor(private val dao: BootCompletedDao) {

    suspend fun addBootCompletedEvent(event: BootEvent) {
        dao.addBootCompletedEvent(event.mapToEntity())
    }

    suspend fun getBootCompletedEvents(): List<BootEvent> =
        dao.getBootCompletedEvents().map { it.mapToModel() }


    fun fetchBootCompletedEvents(): LiveData<List<BootEvent>> =
        dao.fetchBootCompletedEvents().map { it.map { it.mapToModel() } }
}