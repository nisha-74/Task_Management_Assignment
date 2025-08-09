package com.example.taskmanagement.domain.repository
import com.example.taskmanagement.data.local.TaskEntity
import com.example.taskmanagement.domain.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing Task data.
 *
 * This interface abstracts the data operations related to Task,
 * providing methods to add, retrieve, update, and delete tasks.
 * Implementations of this interface will handle data sources such as
 * local database or remote server.
 */
interface TaskRepository {
    /**
     * Adds a new task to the data source.
     *
     * @param task The Task object to add.
     * @throws Exception if the operation fails.
     */
    suspend fun  addTask(task: Task)
    /**
     * Retrieves all tasks as a reactive Flow of List of Tasks.
     *
     * @return Flow emitting the list of all tasks whenever data changes.
     */
    fun  getAllTask(): Flow<List<Task>>
    /**
     * Updates an existing task in the data source.
     *
     * @param task The Task object with updated data.
     * @throws Exception if the operation fails.
     */
    suspend fun editTask(task: Task)
    /**
     * Deletes a task from the data source.
     *
     * @param task The Task object to delete.
     * @throws Exception if the operation fails.
     */
    suspend fun  deleteTask(task: Task)

}