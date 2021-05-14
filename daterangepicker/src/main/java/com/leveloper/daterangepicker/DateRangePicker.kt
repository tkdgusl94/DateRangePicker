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
import com.leveloper.daterangepicker.ext.dayOfMonth
import com.leveloper.daterangepicker.ext.month
import com.leveloper.daterangepicker.ext.year
import com.leveloper.daterangepicker.view.MonthView
import java.util.*

class DateRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): RecyclerView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var rangeChangedLister: RangeChangedListener? = null

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
        calendar.dayOfMonth = 1
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

    fun scrollToDate(date: Calendar) {
        // TODO : 설정한 날짜로 scrollTo
    }

    fun setRangeChangedListener(listener: RangeChangedListener) {
        rangeChangedLister = listener
    }

    /**
     * Adapter
     */
    private inner class DateRangePickerAdapter: RecyclerView.Adapter<DateRangePickerAdapter.ViewHolder>() {

        private val inflater = LayoutInflater.from(context)

        private val items = mutableListOf<MonthDescriptor>()

        private var start: Calendar? = null
        private var end: Calendar? = null

        private val cellClickedListener = object: MonthView.ItemClickListener {
            override fun onItemClick(cell: CellDescriptor) {
                when {
                    start == null -> {
                        start = cell.date
                    }
                    cell.date!!.timeInMillis < start!!.timeInMillis -> {
                        start = cell.date
                        end = null
                    }
                    end == null -> {
                        end = cell.date
                    }
                    else -> {
                        start = cell.date
                        end = null
                    }
                }

                items.forEach {
                    it.updateRangeState(start, end)
                }

                notifyDataSetChanged()

//                rangeChangedLister?.onChanged(start!!, end!!)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = MonthView.create(parent, inflater, cellClickedListener)
            return ViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            (holder.itemView as MonthView).init(items[position])
        }

        fun setItems(items: List<MonthDescriptor>) {
            this.items.clear()

            this.items.addAll(items)
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    }

    interface RangeChangedListener {
        fun onChanged(start: Calendar, end: Calendar)
    }
}