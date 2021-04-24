package com.leveloper.daterangepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.daterangepicker.databinding.ItemMonthBinding
import com.leveloper.daterangepicker.databinding.ItemSampleBinding
import java.util.*

internal class DateRangePickerAdapter: RecyclerView.Adapter<DateRangePickerAdapter.LvViewHolder>() {

    private val items = mutableListOf<Calendar>()

    init {
        val calendar = Calendar.getInstance()

        for (i in 1..100) {
            val cal = calendar.clone() as Calendar
            cal.add(Calendar.MONTH, i)

            items.add(cal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LvViewHolder {
        val binding = ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LvViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class LvViewHolder(private val binding: ItemMonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Calendar) {
            binding.tvMonth.text = (item.get(Calendar.MONTH) + 1).toString()
            binding.cmv.setMonth(item)
        }
    }
}