package com.example.todoapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.main.note.NoteFragment
import com.example.todoapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var notesAdapter: NoteAdapter

    //Other
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //InitViews
        binding?.apply {
            //Support toolbar
            setSupportActionBar(notesToolbar)
            //Note fragment
            addNoteBtn.setOnClickListener {
                NoteFragment().show(supportFragmentManager, NoteFragment().tag)
            }
            //Get Data
            viewModel.getAllNotes()
            viewModel.notesData.observe(this@MainActivity) {
                showEmpty(it.isEmpty)
                notesAdapter.setData(it.data!!)
                noteList.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = notesAdapter
                }
            }
        }
    }

    private fun showEmpty(isShown: Boolean) {
        binding?.apply {
            if (isShown) {
                emptyLay.visibility = View.VISIBLE
                noteList.visibility = View.GONE
            } else {
                emptyLay.visibility = View.GONE
                noteList.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}