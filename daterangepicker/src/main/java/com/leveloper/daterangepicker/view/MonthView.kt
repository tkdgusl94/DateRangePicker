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
import com.leveloper.daterangepicker.data.MonthDescriptor
import com.leveloper.daterangepicker.ext.dayOfMonth
import com.leveloper.daterangepicker.ext.dayOfWeek
import com.leveloper.daterangepicker.ext.month
import com.leveloper.daterangepicker.ext.year
import java.lang.Exception
import java.util.*

internal class MonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): LinearLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)  {

    private val title: TextView
    private var listener: ItemClickListener? = null

    init {
        orientation = VERTICAL

        title = TextView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(50, 50, 0, 20)
        addView(title, params)

        for (i in 0 until MAX_WEEKS_COUNT) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, 150)
            params.setMargins(0, 0, 0, 10)

            val weekView = WeekView(
                ContextThemeWrapper(context, defStyleRes),
                attrs,
                defStyleAttr
            )

            addView(weekView, params)
        }
    }

    fun init(monthDesc: MonthDescriptor) {
        title.text = monthDesc.key

        children.asSequence()
            .filter { it is WeekView }
            .map { it as WeekView }
            .forEachIndexed { i, view ->
                try {
                    val week = monthDesc.weeks[i]

                    view.init(week, listener)
                    view.visibility = View.VISIBLE
                } catch (e: IndexOutOfBoundsException) {
                    view.visibility = View.GONE
                }
            }
    }

    interface ItemClickListener {
        fun onItemClick(cell: CellDescriptor)
    }

    companion object {
        private const val MAX_WEEKS_COUNT = 6

        fun create(
            parent: ViewGroup,
            inflater: LayoutInflater,
            listener: ItemClickListener
        ): MonthView {
            val view = inflater.inflate(R.layout.month, parent, false) as MonthView

            view.listener = listener

            return view
        }
    }
}