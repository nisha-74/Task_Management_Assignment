package com.example.taskmanagement.presentaion.view.actiivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanagement.R
import com.example.taskmanagement.databinding.ActivityAddTaskBinding
import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.presentaion.viewmodel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viwModel: AddViewModel
    private var taskToEdit: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)
        viwModel = ViewModelProvider(this)[AddViewModel::class.java]

        binding.addVm = viwModel
        binding.lifecycleOwner = this

        // Set toolbar as ActionBar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Get the task from intent (handle Android 13+ safely)
        taskToEdit = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("task_to_edit", Task::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Task>("task_to_edit")
        }

        // Set ViewModel LiveData so data appears in UI automatically
        taskToEdit?.let { task ->
            viwModel.title.value = task.title
            viwModel.description.value = task.description
        }

        binding.btnAddTask.setOnClickListener {
            val title = viwModel.title.value ?: ""
            val description = viwModel.description.value ?: ""

            if (title.isBlank() || description.isBlank()) {
                Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultTask = if (taskToEdit != null) {
                taskToEdit!!.copy(title = title, description = description)
            } else {
                Task(title = title, description = description)
            }

            val resultIntent = Intent().apply {
                putExtra("updated_task", resultTask)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Observe messages for Toast
        viwModel.message.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}

