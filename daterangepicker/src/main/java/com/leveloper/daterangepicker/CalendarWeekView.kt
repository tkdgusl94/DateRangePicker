package com.leveloper.daterangepicker

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import java.util.*

class CalendarWeekView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): LinearLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL

        for (i in 0 until Calendar.DAY_OF_WEEK) {
            val cell = CalendarCellView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)

            val params = LayoutParams(0, LayoutParams.MATCH_PARENT)
            params.weight = 1f

            addView(cell, params)
        }
    }

    @Throws
    fun setWeek(weekList: Array<String>) {
        if (weekList.size != Calendar.DAY_OF_WEEK) throw Exception("A week must be seven days.")

        for (i in 0 until childCount) {
            val date = weekList[i]
            (getChildAt(i) as CalendarCellView).setDate(date)
        }
    }
}