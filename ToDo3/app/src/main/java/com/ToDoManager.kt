package com

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

object ToDoManager {
    private val todoList =
        mutableListOf<ToDo>()

    fun getAllToDo(): List<ToDo> {
        return todoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDo(title: String) {
        todoList.add(ToDo(System.currentTimeMillis().toInt(), title,
            Date.from(Instant.now())
        ))
    }

    fun updateToDo(todo: ToDo) {
        val index = todoList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            todoList[index] = todo
        }
              }

    fun deleteToDo(id: Int) {
        todoList.removeIf { it.id == id }
    }

}