package com.leveloper.daterangepicker.ext

import java.util.*

internal var Calendar.year: Int
    get() = get(Calendar.YEAR)
    set(value) = set(Calendar.YEAR, value)

internal var Calendar.month: Int
    get() = get(Calendar.MONTH)
    set(value) = set(Calendar.MONTH, value)

internal var Calendar.dayOfMonth: Int
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) = set(Calendar.DAY_OF_MONTH, value)

internal val Calendar.dayOfWeek: Int
    get() = get(Calendar.DAY_OF_WEEK)

internal fun Calendar.isSameDay(other: Calendar): Boolean {
    return this.year == other.year
            && this.month == other.month
            && this.dayOfMonth == other.dayOfMonth
}