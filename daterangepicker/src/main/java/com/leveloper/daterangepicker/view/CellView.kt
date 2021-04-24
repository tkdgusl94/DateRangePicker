package com.leveloper.daterangepicker.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.leveloper.daterangepicker.data.CellDescriptor
import com.leveloper.daterangepicker.data.RangeState
import com.leveloper.daterangepicker.ext.dayOfMonth
import java.util.*
import kotlin.math.min

class CellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): FrameLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr), View.OnClickListener {

    private var itemClickLister: MonthView.ItemClickListener? = null
    private var desc: CellDescriptor = CellDescriptor.EMPTY

    private val dateTextView: TextView

    private var state: RangeState = RangeState.ONE

    private var pointPaint: Paint = Paint().apply {
        color = Color.YELLOW
    }

    init {
        setWillNotDraw(false)
        setOnClickListener(this)

        dateTextView = TextView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr)

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        }
        addView(dateTextView, params)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null || desc.isEmpty) return

        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()

        val radius = min(centerX, centerY)

        if (RangeState.isDrawCircle(state)) {
            canvas.drawCircle(centerX, centerY, radius, pointPaint)
        }

        if (RangeState.isDrawLeftRect(state)) {
            canvas.drawRect(0f, centerY - radius, centerX, centerY + radius, pointPaint)
        }

        if (RangeState.isDrawRightRect(state)) {
            canvas.drawRect(centerX, centerY - radius, width.toFloat(), centerY + radius, pointPaint)
        }
    }

    fun init(cellDesc: CellDescriptor, listener: MonthView.ItemClickListener?) {
        dateTextView.text = cellDesc.date?.dayOfMonth?.toString()
        itemClickLister = listener

        desc = cellDesc
    }

    override fun onClick(v: View?) {
        itemClickLister?.onItemClick(desc)
    }
}