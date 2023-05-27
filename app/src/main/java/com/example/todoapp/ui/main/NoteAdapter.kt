package com.example.todoapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.NoteEntity
import com.example.todoapp.databinding.ItemNotesBinding
import com.example.todoapp.utils.DELETE
import com.example.todoapp.utils.EDIT
import com.example.todoapp.utils.EDUCATION
import com.example.todoapp.utils.HEALTH
import com.example.todoapp.utils.HIGH
import com.example.todoapp.utils.HOME
import com.example.todoapp.utils.LOW
import com.example.todoapp.utils.NORMAL
import com.example.todoapp.utils.WORK
import javax.inject.Inject

class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    private lateinit var context: Context
    private var moviesList = emptyList<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(moviesList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: NoteEntity) {
            binding.apply {
                titleTxt.text = item.title
                descTxt.text = item.desc
                //Category
                when (item.category) {
                    HOME -> categoryImg.setImageResource(R.drawable.home)
                    WORK -> categoryImg.setImageResource(R.drawable.work)
                    EDUCATION -> categoryImg.setImageResource(R.drawable.education)
                    HEALTH -> categoryImg.setImageResource(R.drawable.healthcare)
                }
                //Priority
                when (item.priority) {
                    HIGH -> priorityColor.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                    NORMAL -> priorityColor.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    LOW -> priorityColor.setBackgroundColor(ContextCompat.getColor(context, R.color.aqua))
                }
                //Menu
                menuImg.setOnClickListener {
                    val popupMenu = PopupMenu(context, it)
                    popupMenu.menuInflater.inflate(R.menu.menu_items, popupMenu.menu)
                    popupMenu.show()
                    //Select
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.itemEdit -> {
                                onItemClickListener?.let {
                                    it(item, EDIT)
                                }
                            }
                            R.id.itemDelete -> {
                                onItemClickListener?.let {
                                    it(item, DELETE)
                                }
                            }
                        }
                        return@setOnMenuItemClickListener true
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((NoteEntity, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoteEntity, String) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<NoteEntity>) {
        val moviesDiffUtil = NotesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NotesDiffUtils(private val oldItem: List<NoteEntity>, private val newItem: List<NoteEntity>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}