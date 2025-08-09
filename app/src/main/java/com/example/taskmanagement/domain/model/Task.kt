package com.example.taskmanagement.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
/**
 * Domain model representing a Task in the application.
 *
 * This class is marked as @Parcelize so it can be easily passed between
 * Android components (Activities, Fragments) through Intents or Bundles.
 * It implements Parcelable to serialize/deserialize its properties efficiently.
 */
@Parcelize
data class Task(
    val id: Int = 0,
    val  title:String,
    val description:String
):Parcelable
