package com.leveloper.daterangepicker

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import java.util.*

class CalendarMonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): LinearLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)  {

    init {
        orientation = VERTICAL

        for (i in 0 until MAX_WEEKS_COUNT) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, 150)
            params.setMargins(0, 0, 0, 10)

            val weekView = CalendarWeekView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)

            addView(weekView, params)
        }
    }

    fun setMonth(calendar: Calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        var index = 0
        val month = calendar.get(Calendar.MONTH)

        var week = Array(7) { "" }

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
                week = Array(7) { "" }
            }
        }

        for (i in index until childCount) {
            getChildAt(i).visibility = View.GONE
        }
    }

    companion object {
        private const val MAX_WEEKS_COUNT = 6
    }
}