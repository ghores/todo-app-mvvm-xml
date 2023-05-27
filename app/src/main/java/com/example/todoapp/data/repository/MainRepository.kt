package com.example.todoapp.data.repository

import com.example.todoapp.data.database.NoteDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: NoteDao) {
    fun allNotes() = dao.getAllNotes()
}