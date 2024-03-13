package com.example.myapplication

import androidx.lifecycle.LiveData

class TodoRepository(private  val todoDao: TodoDao) {
    val readAllNote: LiveData<List<Todo>> = todoDao.getTodo()

    suspend fun insertTodo(todo: Todo){
        todoDao.insert(todo)
    }
    suspend fun  updateTodo(todo: Todo){
        todoDao.update(todo)
    }
    suspend fun deleteTodo(todo: Todo){
        todoDao.delete(todo)
    }
    suspend fun deleteAllTodo(){
        todoDao.deleteAllTodo()
    }
}