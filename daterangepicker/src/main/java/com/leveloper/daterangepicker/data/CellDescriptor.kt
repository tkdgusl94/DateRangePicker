package com.leveloper.daterangepicker.data

import com.leveloper.daterangepicker.ext.month
import com.leveloper.daterangepicker.ext.year
import java.util.*

internal data class CellDescriptor(
    val date: Calendar? = null,
    var state: RangeState = RangeState.NONE
) {
    val key: String
        get() = String.format("%d-%d", date?.year, date?.month)

    val clickable: Boolean
        get() = date != null

    companion object {
        val EMPTY: CellDescriptor
            get() = CellDescriptor()
    }
}