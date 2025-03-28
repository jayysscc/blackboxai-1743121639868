package com.example.scheduler.teacher.repositories

import com.example.scheduler.teacher.models.AvailabilitySlot

interface ScheduleRepository {
    suspend fun getAvailabilitySlots(teacherId: String): List<AvailabilitySlot>
    suspend fun addAvailabilitySlot(slot: AvailabilitySlot)
    suspend fun deleteAvailabilitySlot(slot: AvailabilitySlot)
    suspend fun updateAvailabilitySlot(slot: AvailabilitySlot)
}