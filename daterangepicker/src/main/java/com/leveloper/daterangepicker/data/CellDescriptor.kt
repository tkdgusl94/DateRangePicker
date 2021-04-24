package com.leveloper.daterangepicker.data

import java.util.*

data class CellDescriptor(
    val date: Calendar? = null,
    val isEmpty: Boolean = false
) {

    companion object {
        val EMPTY: CellDescriptor
            get() = CellDescriptor(isEmpty = true)
    }
}