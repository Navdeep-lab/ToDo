package com.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    fun getAllToDo(): LiveData<List<ToDo>>
    @Insert
    fun addToDo(todo: ToDo)
    @Update
    fun updateToDo(todo: ToDo)
    @Query("DELETE FROM ToDo WHERE id = :id")
    fun deleteToDo(id: Int)
}