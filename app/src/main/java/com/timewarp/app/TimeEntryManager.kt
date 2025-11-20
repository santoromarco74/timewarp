package com.timewarp.app

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class TimeEntryManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("timewarp_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val entries = mutableListOf<TimeEntry>()

    companion object {
        private const val KEY_ENTRIES = "time_entries"
    }

    init {
        loadEntries()
    }

    fun clockIn(department: String): TimeEntry {
        val entry = TimeEntry(
            entryTime = System.currentTimeMillis(),
            department = department
        )
        entries.add(entry)
        saveEntries()
        return entry
    }

    fun clockOut(entryId: String): TimeEntry? {
        val entry = entries.find { it.id == entryId && it.isActive() }
        entry?.exitTime = System.currentTimeMillis()
        saveEntries()
        return entry
    }

    fun getActiveEntry(): TimeEntry? {
        return entries.firstOrNull { it.isActive() }
    }

    fun getTodayEntries(): List<TimeEntry> {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN).format(Date())
        return entries.filter { it.date == today }.sortedByDescending { it.entryTime }
    }

    fun getAllEntries(): List<TimeEntry> {
        return entries.sortedByDescending { it.entryTime }
    }

    fun getEntriesForDate(date: String): List<TimeEntry> {
        return entries.filter { it.date == date }.sortedBy { it.entryTime }
    }

    private fun saveEntries() {
        val json = gson.toJson(entries)
        prefs.edit().putString(KEY_ENTRIES, json).apply()
    }

    private fun loadEntries() {
        val json = prefs.getString(KEY_ENTRIES, null)
        if (json != null) {
            val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
            entries.clear()
            entries.addAll(gson.fromJson(json, type))
        }
    }
}
