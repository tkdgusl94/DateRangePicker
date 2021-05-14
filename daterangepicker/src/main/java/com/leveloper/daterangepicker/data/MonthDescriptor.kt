package com.leveloper.daterangepicker.data

import com.leveloper.daterangepicker.ext.dayOfMonth
import com.leveloper.daterangepicker.ext.dayOfWeek
import com.leveloper.daterangepicker.ext.month
import com.leveloper.daterangepicker.ext.year
import java.util.*

internal data class MonthDescriptor(
    val calendar: Calendar
) {
    val key: String
        get() = String.format("%d-%d", calendar.year, calendar.month + 1)

    val weeks: List<Array<CellDescriptor>> by lazy {
        val result = mutableListOf<Array<CellDescriptor>>()

        var week = Array(Calendar.DAY_OF_WEEK) { CellDescriptor.EMPTY }

        val temp = calendar.clone() as Calendar
        while (true) {
            val dayOfWeek = temp.dayOfWeek

            week[dayOfWeek - 1] = CellDescriptor(temp.clone() as Calendar)
            temp.dayOfMonth += 1

            // next month
            if (calendar.month != temp.month) {
                result.add(week)
                break
            }

            if (temp.dayOfWeek == Calendar.SUNDAY) {
                result.add(week)
                week = Array(Calendar.DAY_OF_WEEK) { CellDescriptor.EMPTY }
            }
        }

        result
    }

    fun updateRangeState(start: Calendar?, end: Calendar?) {
        weeks.forEach { week ->
            week.forEach { cell ->
                if (cell.date != null) {
                    if (start!!.timeInMillis <= cell.date.timeInMillis && cell.date.timeInMillis <= end?.timeInMillis ?: start.timeInMillis) {
                        cell.state = RangeState.MIDDLE
                    }
                    else {
                        cell.state = RangeState.NONE
                    }
                }
            }
        }
    }
}