Initial commit: Task Management Android app with Room, MVVM, and Hilt DI

Implemented Task domain model and Room database (TaskEntity, TaskDao, TaskDb) with versioning

Created repository layer (TaskRepository, TaskRepositoryImpl) for data operations using Kotlin Flow

Developed Use Cases for add, update, delete, and fetch tasks with proper validation

Added ViewModel (AddViewModel) handling task logic and LiveData for UI updates

Built RecyclerView adapter (AddTaskAdapter) to display task list with edit and delete functionality

Created two Activities:

MainActivity displaying all tasks with add, edit, delete, and search features

AddTaskActivity for adding or editing tasks, with data binding and form validation

Integrated Hilt for dependency injection, providing database, DAO, and repository instances

UI designed with Material Components, ConstraintLayout, and data binding for smooth UX

Added support for searching tasks by title or description with live filtering

Used AndroidX libraries and Kotlin coroutines for asynchronous operations

Followed MVVM architecture and Clean Code practices for maintainability and scalability

