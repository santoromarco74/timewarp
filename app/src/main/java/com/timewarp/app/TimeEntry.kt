package com.timewarp.app

import java.text.SimpleDateFormat
import java.util.*

data class TimeEntry(
    val id: String = UUID.randomUUID().toString(),
    val entryTime: Long,
    var exitTime: Long? = null,
    val department: String,
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN).format(Date(entryTime))
) {
    fun getHoursWorked(): Double {
        return if (exitTime != null) {
            val diff = exitTime!! - entryTime
            diff / (1000.0 * 60.0 * 60.0)
        } else {
            0.0
        }
    }

    fun getFormattedEntryTime(): String {
        return SimpleDateFormat("HH:mm", Locale.ITALIAN).format(Date(entryTime))
    }

    fun getFormattedExitTime(): String {
        return if (exitTime != null) {
            SimpleDateFormat("HH:mm", Locale.ITALIAN).format(Date(exitTime!!))
        } else {
            "-"
        }
    }

    fun isActive(): Boolean {
        return exitTime == null
    }
}
