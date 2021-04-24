package com.leveloper.daterangepicker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import kotlin.math.min

class CalendarCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): FrameLayout(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var state: RangeState = RangeState.LAST

    private var pointPaint: Paint = Paint().apply {
        color = Color.YELLOW
    }

    init {
        setWillNotDraw(false)

        val date = TextView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr).apply {
            text = "30"
        }

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        }
        addView(date, params)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

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
}