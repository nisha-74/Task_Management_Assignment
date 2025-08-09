package com.example.taskmanagement.domain.usecase

import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Use case for adding a new Task.
 *
 * This class acts as an interactor between the UI layer and the repository.
 * It handles the business logic for adding a task, including validation.
 */
class AddTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun  invoke(task: Task){
        if(task.title.isBlank()||task.description.isBlank()) throw  IllegalArgumentException("Fields can't be blank")
        repository.addTask(task)
    }




}