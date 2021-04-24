package com.leveloper.daterangepicker.data

import java.util.*

internal data class CellDescriptor(
    val date: Calendar? = null,
    var state: RangeState = RangeState.NONE
) {

    companion object {
        val EMPTY: CellDescriptor
            get() = CellDescriptor()
    }
}