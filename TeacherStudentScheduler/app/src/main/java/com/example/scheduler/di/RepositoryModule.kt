package com.example.scheduler.di

import com.example.scheduler.teacher.repositories.FirebaseScheduleRepository
import com.example.scheduler.teacher.repositories.ScheduleRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(firestore: FirebaseFirestore): ScheduleRepository {
        return FirebaseScheduleRepository(firestore)
    }
}