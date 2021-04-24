package com.leveloper.daterangepicker

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DateRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
): RecyclerView(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    init {
        adapter = DateRangePickerAdapter()
        layoutManager = LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes)

    }
}