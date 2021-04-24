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
import com.leveloper.daterangepicker.data.CellDescriptor
import com.leveloper.daterangepicker.data.MonthDescriptor
import com.leveloper.daterangepicker.ext.month
import com.leveloper.daterangepicker.ext.year
import com.leveloper.daterangepicker.view.MonthView
import java.time.Month
import java.util.*

class DateRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): RecyclerView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private val selectedCells = mutableListOf<CellDescriptor>()

    private val listener = CellClickedListener()

    init {
        adapter = DateRangePickerAdapter()
        layoutManager = LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes)
    }

    fun init(start: Calendar, end: Calendar): DateRangePicker {
        if (adapter == null) {
            adapter = DateRangePickerAdapter()
        }

        val months = mutableListOf<MonthDescriptor>()

        val calendar = start.clone() as Calendar
        while (true) {
            months.add(MonthDescriptor(calendar.clone() as Calendar))
            calendar.month += 1

            if (end.year == calendar.year && end.month == calendar.month) {
                break
            }
        }

        (adapter as DateRangePickerAdapter).setItems(months)

        return this
    }

    /**
     * Adapter
     */
    private inner class DateRangePickerAdapter: RecyclerView.Adapter<DateRangePickerAdapter.ViewHolder>() {

        private val inflater = LayoutInflater.from(context)

        private val items = mutableListOf<MonthDescriptor>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = MonthView.create(parent, inflater, listener)
            return ViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val view = holder.itemView as MonthView

            println("!!!!! ${items[position].calendar.year}, ${items[position].calendar.month}")
            view.init(items[position])
        }

        fun setItems(items: List<MonthDescriptor>) {
            this.items.clear()

            this.items.addAll(items)
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    }

    private class CellClickedListener: MonthView.ItemClickListener {
        override fun onItemClick(cell: CellDescriptor) {
        }
    }
}