package com.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ToDo

@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ToDoDatabase : RoomDatabase (){
    companion object {
        const val NAME = "todo_database"
    }
    abstract fun getTodoDao(): ToDoDao

}