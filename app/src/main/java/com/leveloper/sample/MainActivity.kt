package com.leveloper.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leveloper.daterangepicker.CalendarWeekView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = 0
        }

        findViewById<CalendarWeekView>(R.id.cwv).setWeek(
            listOf(
                null,
                null,
                null,
                calendar,
                null,
                null
            )
        )
    }
}