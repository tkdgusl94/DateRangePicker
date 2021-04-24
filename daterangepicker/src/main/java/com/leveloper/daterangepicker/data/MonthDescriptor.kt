package com.leveloper.daterangepicker.data

import com.leveloper.daterangepicker.ext.dayOfMonth
import com.leveloper.daterangepicker.ext.dayOfWeek
import com.leveloper.daterangepicker.ext.month
import java.util.*

data class MonthDescriptor(
    val calendar: Calendar
) {
    val month: Int
        get() = calendar.month + 1

    val weeks: List<Array<CellDescriptor>> by lazy {
        val result = mutableListOf<Array<CellDescriptor>>()

        val month = calendar.month

        var week = Array(Calendar.DAY_OF_WEEK) { CellDescriptor.EMPTY }

        val cal = calendar.clone() as Calendar
        while (true) {
            val dayOfWeek = cal.dayOfWeek

            week[dayOfWeek - 1] = CellDescriptor(cal.clone() as Calendar)
            cal.dayOfMonth += 1

            // next month
            if (month != cal.month) {
                result.add(week)
                break
            }

            if (cal.dayOfWeek == Calendar.SUNDAY) {
                result.add(week)
                week = Array(Calendar.DAY_OF_WEEK) { CellDescriptor.EMPTY }
            }
        }

        result
    }
}