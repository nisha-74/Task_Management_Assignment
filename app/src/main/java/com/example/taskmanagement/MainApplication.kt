package com.example.taskmanagement

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for the Task Management app.
 * Annotated with @HiltAndroidApp to trigger Hilt code generation
 * and enable dependency injection throughout the app.
 */

@HiltAndroidApp
class MainApplication:Application() {
}