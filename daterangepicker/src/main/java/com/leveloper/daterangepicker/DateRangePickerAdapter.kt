package com.leveloper.daterangepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.daterangepicker.databinding.ItemSampleBinding

internal class DateRangePickerAdapter: RecyclerView.Adapter<DateRangePickerAdapter.LvViewHolder>() {

    private val items = mutableListOf<String>()

    init {
        for (i in 1..100) {
            items.add(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LvViewHolder {
        val binding = ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LvViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class LvViewHolder(private val binding: ItemSampleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvDd.text = item
        }
    }
}