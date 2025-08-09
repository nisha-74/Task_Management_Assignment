package com.example.taskmanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanagement.domain.model.Task

/**
 * TaskDb is the Room database class for the Task Management app.
 *
 * - @Database annotation: Specifies that this class is a Room database holder.
 * - entities: List of entity classes (tables) that will be stored in this database.
 * - version: Database version number (used for migrations when schema changes).
 * - abstract class: Must extend RoomDatabase so Room can generate the necessary code.
 */

//  Our database contains a single table for tasks
// Database version (increment when schema changes)
@Database(entities = [TaskEntity::class], version = 3,)
abstract class TaskDb():RoomDatabase() {

    /**
     * Abstract method to get the DAO (Data Access Object) for tasks.
     * Room will generate the implementation at compile time.
     */
    abstract  fun  TaskDao():TaskDao
}