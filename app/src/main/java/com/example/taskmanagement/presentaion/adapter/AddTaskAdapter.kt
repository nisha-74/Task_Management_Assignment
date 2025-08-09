package com.example.taskmanagement.presentaion.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagement.databinding.RecyclerviewItemBinding
import com.example.taskmanagement.domain.model.Task

/**
 * RecyclerView Adapter for displaying a list of Tasks.
 *
 * @param addTaskList The initial list of tasks to display.
 * @param onItemClick Lambda function called when a task item is clicked.
 * @param onDeleteItemClick Lambda function called when delete button of a task is clicked.
 * @param onEditItemClick Lambda function called when edit button of a task is clicked.
 */
class AddTaskAdapter(private var addTaskList:List<Task>,
                     private  val onDeleteItemClick: (Task) -> Unit,
                     private  val onEditItemClick:(Task) -> Unit
   ) :RecyclerView.Adapter<AddTaskAdapter.AddViewHolder>(){
    /**
     * ViewHolder class holding the view binding for each item.
     */
    inner  class AddViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)



     //Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {
       val binding= RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return AddViewHolder(binding)
    }


     //Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return  addTaskList.size
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * Binds the data to the views and sets click listeners.
     */
    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
       val task = addTaskList[position]

        holder.binding.tvTitle.text= task.title
        holder.binding.tvDescription.text= task.description

        holder.binding.editBtn.setOnClickListener {
         onEditItemClick(task)
        }
        holder.binding.deleteBtn.setOnClickListener {
         onDeleteItemClick(task)
        }
    }
    //Updates the list of tasks displayed by the adapter and refreshes the RecyclerView.
    //     * @param newList The new list of tasks to display.
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Task>) {
        addTaskList = newList
        notifyDataSetChanged()  // For simple cases; consider DiffUtil for better performance
    }


}