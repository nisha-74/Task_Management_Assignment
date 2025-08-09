package com.example.taskmanagement.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagement.data.local.TaskEntity
import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.domain.usecase.AddTaskUseCase
import com.example.taskmanagement.domain.usecase.DeleteUseCase
import com.example.taskmanagement.domain.usecase.EditUseCase
import com.example.taskmanagement.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private  val addTaskUseCase: AddTaskUseCase,
                                       private val updateTaskUseCase: EditUseCase,
                                       private val deleteTaskUseCase:DeleteUseCase, private val getAllTasksUseCase: GetAllTasksUseCase,):ViewModel() {

    var title= MutableLiveData("")
    var description= MutableLiveData("")
    var message=MutableLiveData<String>()
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks
    init {
        // Collect tasks flow and update LiveData
        viewModelScope.launch {
           getAllTasksUseCase()
                .collect { taskList ->
                    _tasks.value = taskList
                    Log.d("ViewModel", "Tasks count:${_tasks.value } ${taskList.size}")
                }
        }
    }

    fun addTask(newTask: Task) {
       viewModelScope.launch{
           try {
               addTaskUseCase(newTask


               )
               message.value = "Task is added"

           }
           catch (e:Exception){
               message.value=e.toString();
               Log.d("adderr", e.toString())

           }

       }
   }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                updateTaskUseCase(task)
                message.value = "Task updated successfully"
            } catch (e: Exception) {
                message.value = "Error updating task: ${e.message}"
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase(task)
                message.value = "Task deleted successfully"
            } catch (e: Exception) {
                message.value = "Error deleting task: ${e.message}"
            }
        }
    }


}