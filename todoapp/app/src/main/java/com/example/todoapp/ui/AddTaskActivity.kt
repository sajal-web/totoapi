package com.example.todoapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.todoapp.R
import com.example.todoapp.data.api.Task
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.ui.addtask.TaskViewModelFactory
import com.example.todoapp.ui.viewmodel.TaskViewModel

class AddTaskActivity : AppCompatActivity() {
    private val repository = TaskRepository()
    private val viewModel: TaskViewModel by viewModels { TaskViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etDueDate = findViewById<EditText>(R.id.etDueDate)
        val cbStatus = findViewById<CheckBox>(R.id.cbStatus)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val dueDate = etDueDate.text.toString()
            val status = cbStatus.isChecked

            val task = Task(title, description, status, dueDate)
            viewModel.createTask(task)
        }

        viewModel.taskResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Toast.makeText(this, "Task added successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add task!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}