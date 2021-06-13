package com.ihfazh.absensiqrcode.ui.detailevent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ihfazh.absensiqrcode.databinding.SimpleListItemBinding
import com.ihfazh.absensiqrcode.domains.attendances.models.StudentAttendance

class HistoriesAdapter: PagingDataAdapter<StudentAttendance, HistoriesAdapter.ViewHolder>(DIFF_UTIL) {
    class ViewHolder(private val binding: SimpleListItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: StudentAttendance) {
            binding.itemText.text = "${item.student.firstName} ${item.student.lastName} at ${item.date}"
        }

    }

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<StudentAttendance>(){
            override fun areItemsTheSame(oldItem: StudentAttendance, newItem: StudentAttendance): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StudentAttendance, newItem: StudentAttendance): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null){
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimpleListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
}