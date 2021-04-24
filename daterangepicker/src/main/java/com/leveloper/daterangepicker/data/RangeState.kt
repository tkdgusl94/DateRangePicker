package com.leveloper.daterangepicker.data

enum class RangeState {
    NONE, ONE, FIRST, MIDDLE, LAST;

    companion object {

        private val CIRCLE = setOf(ONE, FIRST, LAST)
        private val LEFT_RECT = setOf(MIDDLE, LAST)
        private val RIGHT_RECT = setOf(MIDDLE, FIRST)

        fun isDrawCircle(state: RangeState) = CIRCLE.contains(state)

        fun isDrawLeftRect(state: RangeState) = LEFT_RECT.contains(state)

        fun isDrawRightRect(state: RangeState) = RIGHT_RECT.contains(state)
    }
}