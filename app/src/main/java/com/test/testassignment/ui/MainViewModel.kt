package com.test.testassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.testassignment.models.BootEvent
import com.test.testassignment.repository.BootEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: BootEventsRepository) : ViewModel() {
    val bootEventsLiveData: LiveData<List<BootEvent>> = liveData(Dispatchers.IO) {
        emitSource(repository.fetchBootCompletedEvents())
    }
}