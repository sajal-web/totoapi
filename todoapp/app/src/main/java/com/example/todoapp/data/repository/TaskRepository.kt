package com.example.todoapp.data.repository

import com.example.todoapp.data.api.ApiService
import com.example.todoapp.data.api.Task
import retrofit2.Response


class TaskRepository {
    suspend fun createTask(task: Task): Response<Task> {
        return ApiService.api.createTask(task)
    }
}


