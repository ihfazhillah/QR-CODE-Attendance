package com.ihfazh.absensiqrcode.ui.liststudents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ihfazh.absensiqrcode.databinding.SimpleListItemBinding
import Event.Student

class ListStudentAdapter: PagingDataAdapter<Student, ListStudentAdapter.StudentItemViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Student>(){
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentId == newItem.studentId
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    class StudentItemViewHolder(private val binding: SimpleListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student?) {
            item?.let {
                binding.itemText.text = it.firstName
            }
        }

    }


    override fun onBindViewHolder(holder: StudentItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleListItemBinding.inflate(inflater, parent, false)
        return StudentItemViewHolder(binding)
    }
}