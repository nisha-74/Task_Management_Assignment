//package com.example.taskmanagement.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.taskmanagement.data.local.TaskDb
//import com.example.taskmanagement.data.repository.TaskRepositoryImpl
//import com.example.taskmanagement.domain.repository.TaskRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context):TaskDb=
//        Room.databaseBuilder(context,TaskDb::class.java, "Task_db").
//            fallbackToDestructiveMigration()
//                .build()
//
//    @Provides
//    fun provideTaskDao(db:TaskDb)=db.TaskDao()
//
//    @Provides
//    fun provideRepository(impl:TaskRepositoryImpl):TaskRepository=impl
//
//
//
//}

package com.example.taskmanagement.di

import android.content.Context
import androidx.room.Room
import com.example.taskmanagement.data.local.TaskDb
import com.example.taskmanagement.data.repository.TaskRepositoryImpl
import com.example.taskmanagement.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module to provide app-wide dependencies.
 *
 * This module provides:
 * - Singleton instance of the Room database (TaskDb)
 * - DAO instance from the database
 * - Repository implementation for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of TaskDb (Room database).
     * Uses fallbackToDestructiveMigration to reset database on version mismatch.
     *
     * @param context Application context injected by Hilt
     * @return TaskDb instance
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDb =
        Room.databaseBuilder(context, TaskDb::class.java, "Task_db")
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Provides TaskDao instance from the provided TaskDb.
     *
     * @param db TaskDb instance
     * @return TaskDao for database operations
     */
    @Provides
    fun provideTaskDao(db: TaskDb) = db.TaskDao()

    /**
     * Provides the TaskRepository implementation.
     * This binds TaskRepositoryImpl to the TaskRepository interface.
     *
     * @param impl TaskRepositoryImpl instance injected by Hilt
     * @return TaskRepository interface instance
     */
    @Provides
    fun provideRepository(impl: TaskRepositoryImpl): TaskRepository = impl

}
