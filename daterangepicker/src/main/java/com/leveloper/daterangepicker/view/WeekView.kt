package com.leveloper.daterangepicker.view

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.leveloper.daterangepicker.data.CellDescriptor
import java.util.*

internal class WeekView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): LinearLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL

        for (i in 0 until Calendar.DAY_OF_WEEK) {
            val cell = CellView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)

            val params = LayoutParams(0, LayoutParams.MATCH_PARENT)
            params.weight = 1f

            addView(cell, params)
        }
    }

    fun init(weekList: Array<CellDescriptor>, listener: MonthView.ItemClickListener?) {
        if (weekList.size != Calendar.DAY_OF_WEEK) throw Exception("A week must be seven days.")

        for (i in 0 until childCount) {
            val date = weekList[i]
            (getChildAt(i) as CellView).init(date, listener)
        }
    }
}