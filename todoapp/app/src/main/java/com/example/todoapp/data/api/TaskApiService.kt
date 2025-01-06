package com.example.todoapp.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class Task(
    val title: String,
    val description: String,
    val status: Boolean,
    val due_date: String
)

interface TaskApiService {
    @POST("/create_task")
    suspend fun createTask(@Body task: Task): Response<Task>
}


