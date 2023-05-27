package com.example.todoapp.ui.main.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentNoteBinding
import com.example.todoapp.utils.setupListWithAdapter
import com.example.todoapp.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : BottomSheetDialogFragment() {
    //Binding
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding


    //Other
    private val viewModel: NoteViewModel by viewModels()
    private var category = ""
    private var priority = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {
            //Spinner Category
            viewModel.loadCategoriesData()
            viewModel.categoriesList.observe(viewLifecycleOwner) {
                categoriesSpinner.setupListWithAdapter(it) { itItem ->
                    category = itItem
                }
            }
            //Spinner Category
            viewModel.loadPrioritiesData()
            viewModel.prioritiesList.observe(viewLifecycleOwner) {
                prioritySpinner.setupListWithAdapter(it) { itItem ->
                    priority = itItem
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }
}