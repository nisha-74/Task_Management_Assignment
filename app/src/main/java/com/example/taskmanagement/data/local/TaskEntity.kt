package com.example.taskmanagement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanagement.domain.model.Task

/** Represents a database entity for storing tasks in a Room database table named "task"*/
@Entity(tableName = "task")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val  id: Int=0,
    val title:String,
    val description:String
)
/** Extension function to convert a domain model Task object into a TaskEntity,
 * which is the format suitable for database storage.***/
fun Task.toEntity()= TaskEntity(id=id, title = title, description = description)

/** Extension function to convert a TaskEntity (database model) back into
a domain model Task object used in the app’s business logic. **/
fun TaskEntity.toDomain(): Task = Task(
    id = id,
    title =title,
    description = description
)
