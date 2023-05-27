package com.example.todoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.utils.EDUCATION
import com.example.todoapp.utils.HEALTH
import com.example.todoapp.utils.HIGH
import com.example.todoapp.utils.HOME
import com.example.todoapp.utils.LOW
import com.example.todoapp.utils.NORMAL
import com.example.todoapp.utils.WORK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor() : ViewModel() {
    //Spinners
    val categoriesList = MutableLiveData<MutableList<String>>()
    val prioritiesList = MutableLiveData<MutableList<String>>()

    fun loadCategoriesData() = viewModelScope.launch(Dispatchers.IO) {
        val data = mutableListOf<String>(WORK, EDUCATION, HOME, HEALTH)
        categoriesList.postValue(data)
    }

    fun loadPrioritiesData() = viewModelScope.launch(Dispatchers.IO) {
        val data = mutableListOf<String>(HIGH, NORMAL, LOW)
        prioritiesList.postValue(data)
    }
}