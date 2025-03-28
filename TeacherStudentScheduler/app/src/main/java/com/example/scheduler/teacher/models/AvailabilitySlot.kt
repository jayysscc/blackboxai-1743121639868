package com.example.scheduler.teacher.models

import java.util.UUID

data class AvailabilitySlot(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val time: String,
    val duration: String,
    val teacherId: String
) {
    companion object {
        fun fromMap(map: Map<String, Any>): AvailabilitySlot {
            return AvailabilitySlot(
                id = map["id"] as? String ?: UUID.randomUUID().toString(),
                date = map["date"] as String,
                time = map["time"] as String,
                duration = map["duration"] as String,
                teacherId = map["teacherId"] as String
            )
        }
    }

    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "date" to date,
            "time" to time,
            "duration" to duration,
            "teacherId" to teacherId
        )
    }
}