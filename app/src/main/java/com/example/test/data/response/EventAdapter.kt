package com.example.test.data.response

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemEventBinding

class EventAdapter : ListAdapter<ListEventsItem, EventAdapter.EventViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: ListEventsItem) {
            binding.tvTitle.text = event.name ?: "No Title"
            binding.tvDate.text = event.beginTime ?: "No Date"
            binding.tvDescription.text = event.summary

            Glide.with(binding.root.context)
                .load(event.mediaCover)
                .into(binding.ivEventImage)

            binding.btnLink.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_EVENT_NAME, event.name)
                intent.putExtra(DetailActivity.EXTRA_EVENT_DATE, event.beginTime)
                intent.putExtra(DetailActivity.EXTRA_EVENT_DESCRIPTION, event.summary)
                intent.putExtra(DetailActivity.EXTRA_EVENT_IMAGE, event.mediaCover)
                intent.putExtra(DetailActivity.EXTRA_EVENT_LINK, event.link)
                intent.putExtra(DetailActivity.EXTRA_EVENT_ORGANIZER, event.ownerName)
                intent.putExtra(DetailActivity.EXTRA_EVENT_QUOTA, event.quota)
                intent.putExtra(DetailActivity.EXTRA_EVENT_REGISTRANTS, event.registrants)
                intent.putExtra(DetailActivity.EXTRA_EVENT_FULL_DESCRIPTION, event.description)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}


