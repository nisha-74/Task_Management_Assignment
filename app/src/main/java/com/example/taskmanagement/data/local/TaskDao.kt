package com.example.taskmanagement.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanagement.domain.model.Task

import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for performing CRUD operations on TaskEntity objects
 * in the Room database. This interface defines the methods used to interact with
 * the underlying "task" table.
 */
@Dao
interface TaskDao {

    /**
     * Inserts a new TaskEntity into the database.
     *
     * If a task with the same primary key already exists, it will be replaced
     * due to the OnConflictStrategy.REPLACE strategy.
     *
     * @param task The TaskEntity to be added or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addTask(task: TaskEntity)

    /**
     * Retrieves all tasks from the "task" table as a Flow that emits
     * updates whenever the data changes.
     *
     * @return A Flow emitting a list of all TaskEntity objects in the database.
     */
    @Query("SELECT * FROM task")
    fun getTask(): Flow<List<TaskEntity>>
    /**
     * Updates an existing TaskEntity in the database.
     * The task is matched by its primary key (id).
     *
     * @param task The TaskEntity with updated values.
     */
    @Update
    suspend fun updateTask(task: TaskEntity)
    /**
     * Deletes the specified TaskEntity from the database.
     *
     * @param task The TaskEntity to be removed.
     */
    @Delete
    suspend fun deleteTask(task: TaskEntity)
}