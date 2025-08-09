package com.example.taskmanagement.data.repository


import com.example.taskmanagement.data.local.TaskDao
import com.example.taskmanagement.data.local.TaskEntity
import com.example.taskmanagement.data.local.toDomain
import com.example.taskmanagement.data.local.toEntity
import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.map

/**
 * TaskRepositoryImpl is the concrete implementation of the TaskRepository interface.
 * It acts as the data layer mediator between the domain layer and the local Room database.
 *
 * This class handles all CRUD operations on Task data by interacting with TaskDao.
 * It converts between domain Task models and database TaskEntity models as needed.
 *
 * Main responsibilities:
 * - Add new tasks to the database
 * - Retrieve all tasks as a Flow stream
 * - Update existing tasks
 * - Delete tasks from the database
 *
 * @property taskDao Data Access Object for database operations
 */
class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {

    /**
     * Adds a new Task to the database.
     * Converts the Task domain model to TaskEntity for persistence.
     *
     * @param task Task domain model to add
     */
    override suspend fun addTask(task: Task) {
        taskDao.addTask(task.toEntity())
    }

    /**
     * Retrieves all tasks from the database as a Flow.
     * Maps the list of TaskEntity from the DB into the domain Task model.
     *
     * @return Flow emitting a list of Task domain models
     */
    override fun getAllTask(): Flow<List<Task>> {
        return taskDao.getTask()
            .map { entityList ->
                entityList.map { it.toDomain() }
            }
    }

    /**
     * Updates an existing Task in the database.
     * Converts the domain Task to TaskEntity before updating.
     *
     * @param task Task domain model with updated fields
     */
    override suspend fun editTask(task: Task) {
        taskDao.updateTask(task.toEntity())
    }

    /**
     * Deletes a Task from the database.
     * Converts the domain Task to TaskEntity before deletion.
     *
     * @param task Task domain model to delete
     */
    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toEntity())
    }
}



//class TaskRepositoryImpl @Inject constructor(private  val taskDao: TaskDao):TaskRepository {
//
//    override suspend fun addTask(task: Task) {
//        taskDao.addTask(task.toEntity())
//    }
//
//    override fun getAllTask(): Flow<List<Task>> {
//        return taskDao.getTask().map { entityList ->
//            entityList.map {
//                it.toDomain()
//            }
//        }
//
//    }
//
//    override suspend fun editTask(task: Task) {
//        taskDao.updateTask(task.toEntity())
//    }
//
//    override suspend fun deleteTask(task: Task) {  taskDao.deleteTask(task.toEntity())
//    }
//}
//
