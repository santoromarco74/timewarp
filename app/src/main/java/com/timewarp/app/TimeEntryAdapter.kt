package com.timewarp.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TimeEntryAdapter(private var entries: List<TimeEntry>) : 
    RecyclerView.Adapter<TimeEntryAdapter.TimeEntryViewHolder>() {

    class TimeEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDepartment: TextView = view.findViewById(R.id.tvDepartment)
        val tvEntryTime: TextView = view.findViewById(R.id.tvEntryTime)
        val tvExitTime: TextView = view.findViewById(R.id.tvExitTime)
        val tvHoursWorked: TextView = view.findViewById(R.id.tvHoursWorked)
        val tvActiveIndicator: TextView = view.findViewById(R.id.tvActiveIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_time_entry, parent, false)
        return TimeEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeEntryViewHolder, position: Int) {
        val entry = entries[position]
        
        holder.tvDepartment.text = entry.department
        holder.tvEntryTime.text = entry.getFormattedEntryTime()
        holder.tvExitTime.text = entry.getFormattedExitTime()
        
        val hours = entry.getHoursWorked()
        holder.tvHoursWorked.text = if (hours > 0) {
            String.format(Locale.ITALIAN, "%.2f ore", hours)
        } else {
            "-"
        }
        
        if (entry.isActive()) {
            holder.tvActiveIndicator.visibility = View.VISIBLE
        } else {
            holder.tvActiveIndicator.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = entries.size

    fun updateEntries(newEntries: List<TimeEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}
