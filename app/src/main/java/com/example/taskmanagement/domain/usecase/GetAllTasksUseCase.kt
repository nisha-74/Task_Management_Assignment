package com.example.taskmanagement.domain.usecase

import com.example.taskmanagement.data.local.TaskEntity
import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTask()
    }
}
