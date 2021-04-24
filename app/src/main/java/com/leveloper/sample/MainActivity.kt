package com.leveloper.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leveloper.daterangepicker.CalendarMonthView
import com.leveloper.daterangepicker.CalendarWeekView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = 0
        }

//        calendar.add(Calendar.MONTH, 4)
//        findViewById<CalendarMonthView>(R.id.cmv).setMonth(calendar)
    }
}