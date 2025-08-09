package com.example.taskmanagement.presentaion.view.actiivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagement.R
import com.example.taskmanagement.databinding.ActivityMainBinding
import com.example.taskmanagement.domain.model.Task
import com.example.taskmanagement.presentaion.adapter.AddTaskAdapter
import com.example.taskmanagement.presentaion.viewmodel.AddViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AddViewModel
    private lateinit var adapter: AddTaskAdapter
    private var fullTaskList: List<Task> = emptyList()

    private val updateTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = result.data?.getParcelableExtra<Task>("updated_task")
            task?.let {
                if (it.id == 0) {
                    viewModel.addTask(it)
                } else {
                    viewModel.updateTask(it)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[AddViewModel::class.java]

        adapter = AddTaskAdapter(
            addTaskList = emptyList(),
            onItemClick = { task ->
                // Handle click if needed
            },
            onDeleteItemClick = { task ->
                viewModel.deleteTask(task)
            },
            onEditItemClick = { task ->
                val intent = Intent(this, AddTaskActivity::class.java)
                intent.putExtra("task_to_edit", task)
                updateTaskLauncher.launch(intent)  // <-- Use launcher here!
            }
        )

        binding.reView.layoutManager = LinearLayoutManager(this)
        binding.reView.adapter = adapter

        viewModel.tasks.observe(this) { taskList ->
            fullTaskList = taskList
            adapter.submitList(taskList)
        }

        viewModel.message.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        binding.addTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            updateTaskLauncher.launch(intent)
        }
        val searchEditText = binding.searchEditText

        searchEditText.addTextChangedListener { editable ->
            val query = editable?.toString()?.trim() ?: ""
            filterTasks(query)
        }

    }
    private fun filterTasks(query: String) {
        val filteredList = if (query.isEmpty()) {
            fullTaskList
        } else {
            fullTaskList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        adapter.submitList(filteredList)
    }

}

