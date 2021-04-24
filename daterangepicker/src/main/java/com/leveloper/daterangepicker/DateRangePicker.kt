package com.leveloper.daterangepicker

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DateRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): RecyclerView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

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
}