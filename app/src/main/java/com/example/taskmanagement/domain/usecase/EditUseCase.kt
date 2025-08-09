package com.example.taskmanagement.domain.usecase

import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.domain.repository.TaskRepository
import javax.inject.Inject

class EditUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.editTask(task)
    }
}