package com.example.myapplication
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    val readAllTodo: LiveData<List<Todo>>

    private val todoRepository: TodoRepository

    init {
        val todoDao = TodoDataBase.getDataBase(application).todoDao()
        todoRepository = TodoRepository(todoDao)
        readAllTodo = todoRepository.readAllNote
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun deleteAllTodo() {
        viewModelScope.launch {
            todoRepository.deleteAllTodo()
        }
    }
}