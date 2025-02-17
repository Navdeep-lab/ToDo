package com

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val createdAt: Date
)

