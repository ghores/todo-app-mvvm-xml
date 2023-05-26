package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.utils.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var desc: String = "",
    var category: String = "",
    var priority: String = ""
)