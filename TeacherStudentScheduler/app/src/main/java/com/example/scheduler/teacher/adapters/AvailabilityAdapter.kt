package com.example.scheduler.teacher.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduler.databinding.ItemAvailabilitySlotBinding
import com.example.scheduler.teacher.models.AvailabilitySlot

class AvailabilityAdapter(
    private val onDeleteClick: (AvailabilitySlot) -> Unit
) : ListAdapter<AvailabilitySlot, AvailabilityAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAvailabilitySlotBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemAvailabilitySlotBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(slot: AvailabilitySlot) {
            binding.tvDate.text = slot.date
            binding.tvTime.text = slot.time
            binding.tvDuration.text = slot.duration
            binding.btnDelete.setOnClickListener { onDeleteClick(slot) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AvailabilitySlot>() {
        override fun areItemsTheSame(oldItem: AvailabilitySlot, newItem: AvailabilitySlot) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AvailabilitySlot, newItem: AvailabilitySlot) =
            oldItem == newItem
    }
}