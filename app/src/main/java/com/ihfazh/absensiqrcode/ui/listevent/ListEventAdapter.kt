package com.ihfazh.absensiqrcode.ui.listevent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ihfazh.absensiqrcode.databinding.SimpleListItemBinding
import com.ihfazh.absensiqrcode.domains.events.models.Event

typealias ClickHandler = (item: Event?) -> Unit

class ListEventAdapter : PagingDataAdapter<Event, ListEventAdapter.ListEventViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.eventId == newItem.eventId
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ListEventViewHolder(private val binding: SimpleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Event?) {
            if (item != null) {
                binding.itemText.text = "Event: ${item.title} (${item.datetime})"
            }
        }

    }



    private var onItemClicked : ClickHandler? = null
    fun setOnItemClicked(onItemClicked: ((item: Event?) -> Unit)?){
        this.onItemClicked = onItemClicked
    }


    override fun onBindViewHolder(holder: ListEventViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            if (item != null){
                onItemClicked?.invoke(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEventViewHolder {
        val binding = SimpleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListEventViewHolder(binding)
    }
}