package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.api.Task
import com.example.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val taskResponse: MutableLiveData<Response<Task>> = MutableLiveData()

    fun createTask(task: Task) {
        viewModelScope.launch {
            val response = repository.createTask(task)
            taskResponse.postValue(response)
        }
    }

}


