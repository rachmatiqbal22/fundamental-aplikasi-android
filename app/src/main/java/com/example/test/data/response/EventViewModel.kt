package com.example.test.data.response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val repository = EventRepository()

    private val _events = MutableLiveData<List<ListEventsItem>>()
    val events: LiveData<List<ListEventsItem>> = _events

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getEvents(active: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getEvents(active)
                if (response.isSuccessful) {
                    val eventList = response.body()?.listEvents ?: emptyList()
                    _events.value = eventList
                    _error.value = null
                } else {
                    _error.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
