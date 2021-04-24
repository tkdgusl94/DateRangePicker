package com.leveloper.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leveloper.daterangepicker.CalendarMonthView
import com.leveloper.daterangepicker.CalendarWeekView
import com.leveloper.daterangepicker.DateRangePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = Calendar.getInstance().apply { timeInMillis = 0 }
        val end = Calendar.getInstance()
//        val end = start.clone() as Calendar
//
//        end.add(Calendar.MONTH, 10)
        findViewById<DateRangePicker>(R.id.drp).init(start, end)
//        calendar.add(Calendar.MONTH, 4)
//        findViewById<CalendarMonthView>(R.id.cmv).setMonth(calendar)
    }
}