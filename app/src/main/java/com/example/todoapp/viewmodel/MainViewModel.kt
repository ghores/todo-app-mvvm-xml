package com.example.todoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.NoteEntity
import com.example.todoapp.data.repository.MainRepository
import com.example.todoapp.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    var notesData = MutableLiveData<DataStatus<List<NoteEntity>>>()

    fun getAllNotes() = viewModelScope.launch {
        repository.allNotes().collect {
            notesData.postValue(DataStatus.success(it, it.isEmpty()))
        }
    }

}