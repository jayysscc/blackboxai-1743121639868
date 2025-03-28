package com.example.scheduler.teacher.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduler.teacher.models.AvailabilitySlot
import com.example.scheduler.teacher.repositories.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {
    private val _availabilitySlots = MutableLiveData<List<AvailabilitySlot>>()
    val availabilitySlots: LiveData<List<AvailabilitySlot>> = _availabilitySlots

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadAvailabilitySlots(teacherId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _availabilitySlots.value = repository.getAvailabilitySlots(teacherId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load availability slots"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addAvailabilitySlot(slot: AvailabilitySlot) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.addAvailabilitySlot(slot)
                loadAvailabilitySlots(slot.teacherId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add availability slot"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteAvailabilitySlot(slot: AvailabilitySlot) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.deleteAvailabilitySlot(slot)
                loadAvailabilitySlots(slot.teacherId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete availability slot"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}