package com.leveloper.daterangepicker.view

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.view.children
import com.leveloper.daterangepicker.R
import com.leveloper.daterangepicker.data.CellDescriptor
import java.util.*

class CalendarMonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): LinearLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)  {

    private val title: TextView

    init {
        orientation = VERTICAL

        title = TextView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(20, 20, 0, 10)
        addView(title, params)

        for (i in 0 until MAX_WEEKS_COUNT) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, 150)
            params.setMargins(0, 0, 0, 10)

            val weekView = CalendarWeekView(
                ContextThemeWrapper(context, defStyleRes),
                attrs,
                defStyleAttr
            )

            addView(weekView, params)
        }
    }

    fun init(calendar: Calendar) {
        title.text = calendar.get(Calendar.MONTH + 1).toString()

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        var index = 1
        val month = calendar.get(Calendar.MONTH)

        var week = Array(Calendar.DAY_OF_WEEK) { "" }

        fun setWeek(week: Array<String>) {
            val weekView = getChildAt(index++) as CalendarWeekView
            weekView.setWeek(week)
            weekView.visibility = View.VISIBLE
        }

        while (true) {
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            week[dayOfWeek - 1] = calendar.get(Calendar.DAY_OF_MONTH).toString()
            calendar.add(Calendar.DAY_OF_MONTH, 1)

            // next month
            if (month != calendar.get(Calendar.MONTH)) {
                setWeek(week)
                break
            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                setWeek(week)
                week = Array(Calendar.DAY_OF_WEEK) { "" }
            }
        }

        for (i in index until childCount) {
            getChildAt(i).visibility = View.GONE
        }
    }

    fun setMonth(calendar: Calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        var index = 1
        val month = calendar.get(Calendar.MONTH)

        var week = Array(Calendar.DAY_OF_WEEK) { "" }

        fun setWeek(week: Array<String>) {
            val weekView = getChildAt(index++) as CalendarWeekView
            weekView.setWeek(week)
            weekView.visibility = View.VISIBLE
        }

        while (true) {
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            week[dayOfWeek - 1] = calendar.get(Calendar.DAY_OF_MONTH).toString()
            calendar.add(Calendar.DAY_OF_MONTH, 1)

            // next month
            if (month != calendar.get(Calendar.MONTH)) {
                setWeek(week)
                break
            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                setWeek(week)
                week = Array(Calendar.DAY_OF_WEEK) { "" }
            }
        }

        for (i in index until childCount) {
            getChildAt(i).visibility = View.GONE
        }
    }

    fun notify(selectedStart: Calendar, selectedEnd: Calendar) {
        children
            .filter { it.visibility == View.VISIBLE }
            .map { it as CalendarWeekView }
            .forEach {

            }
    }

    interface ItemClickListener {
        fun onItemClick(cell: CellDescriptor)
    }

    companion object {
        private const val MAX_WEEKS_COUNT = 6

        fun create(
            parent: ViewGroup,
            inflater: LayoutInflater
        ): CalendarMonthView {
            val view = inflater.inflate(R.layout.month, parent, false) as CalendarMonthView
            return view
        }
    }
}