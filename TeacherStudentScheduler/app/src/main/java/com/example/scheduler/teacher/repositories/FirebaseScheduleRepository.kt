package com.example.scheduler.teacher.repositories

import com.example.scheduler.teacher.models.AvailabilitySlot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseScheduleRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : ScheduleRepository {
    private companion object {
        const val AVAILABILITY_COLLECTION = "availability_slots"
    }

    override suspend fun getAvailabilitySlots(teacherId: String): List<AvailabilitySlot> {
        return firestore.collection(AVAILABILITY_COLLECTION)
            .whereEqualTo("teacherId", teacherId)
            .get()
            .await()
            .documents
            .map { doc -> 
                AvailabilitySlot.fromMap(doc.data!!.toMutableMap().apply { 
                    put("id", doc.id) 
                })
            }
    }

    override suspend fun addAvailabilitySlot(slot: AvailabilitySlot) {
        firestore.collection(AVAILABILITY_COLLECTION)
            .add(slot.toMap())
            .await()
    }

    override suspend fun deleteAvailabilitySlot(slot: AvailabilitySlot) {
        firestore.collection(AVAILABILITY_COLLECTION)
            .document(slot.id)
            .delete()
            .await()
    }

    override suspend fun updateAvailabilitySlot(slot: AvailabilitySlot) {
        firestore.collection(AVAILABILITY_COLLECTION)
            .document(slot.id)
            .set(slot.toMap())
            .await()
    }
}