package com.test.testassignment.ui

import androidx.lifecycle.ViewModel
import com.test.testassignment.repository.BootEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: BootEventsRepository): ViewModel() {

}