package com.leveloper.daterangepicker

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.daterangepicker.view.CalendarMonthView
import java.util.*

class DateRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): RecyclerView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var selectedStart = Calendar.getInstance()
    private var selectedEnd = Calendar.getInstance()

    init {
        adapter = DateRangePickerAdapter()
        layoutManager = LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes)
    }

    fun init(start: Calendar, end: Calendar): DateRangePicker {
        if (adapter == null) {
            adapter = DateRangePickerAdapter()
        }

        val months = mutableListOf<Calendar>()

        val calendar = start.clone() as Calendar
        while (true) {
            months.add(calendar.clone() as Calendar)
            calendar.add(Calendar.MONTH, 1)

            if (end.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && end.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                break
            }
        }

        (adapter as DateRangePickerAdapter).setItems(months)
        return this
    }

    private fun notify2() {
        (adapter as DateRangePickerAdapter).notify2()
    }

    private inner class DateRangePickerAdapter: RecyclerView.Adapter<DateRangePickerAdapter.ViewHolder>() {

        private val inflater = LayoutInflater.from(context)

        private val items = mutableListOf<Calendar>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = CalendarMonthView.create(parent, inflater)
            return ViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val view = holder.itemView as CalendarMonthView

            view.init(items[position])
        }

        fun setItems(items: List<Calendar>) {
            this.items.clear()

            this.items.addAll(items)
            notifyDataSetChanged()
        }

        fun notify2() {
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    }
}