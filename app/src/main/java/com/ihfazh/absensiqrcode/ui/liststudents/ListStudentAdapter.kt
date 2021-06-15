package com.ihfazh.absensiqrcode.ui.liststudents

import Event.Student
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ihfazh.absensiqrcode.databinding.CardStudentItemBinding
import net.glxn.qrgen.android.QRCode

class ListStudentAdapter :
    PagingDataAdapter<Student, ListStudentAdapter.StudentItemViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentId == newItem.studentId
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    var clickListener: OnStudentItemClicked? = null

    interface OnStudentItemClicked {
        fun onItemClicked(student: Student)
    }

    class StudentItemViewHolder(val binding: CardStudentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student?) {
            item?.let {
                binding.text.text = "${it.firstName} ${it.lastName}"
                val code = "attendanceqrcode.${it.studentId}"
                Glide.with(binding.root)
                        .load(QRCode.from(code).bitmap())

                        .into(binding.qrcode)
            }
        }

    }


    override fun onBindViewHolder(holder: StudentItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            item?.let {
                clickListener?.onItemClicked(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardStudentItemBinding.inflate(inflater, parent, false)
        return StudentItemViewHolder(binding)
    }
}