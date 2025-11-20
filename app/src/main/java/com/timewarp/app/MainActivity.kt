package com.timewarp.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var timeEntryManager: TimeEntryManager
    private lateinit var adapter: TimeEntryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: View
    private lateinit var departmentSpinner: AutoCompleteTextView
    private lateinit var btnClockIn: MaterialButton
    private lateinit var btnClockOut: MaterialButton
    private lateinit var btnExport: MaterialButton

    private val departments = arrayOf(
        "Produzione",
        "Amministrazione",
        "Logistica",
        "Manutenzione",
        "Qualità",
        "Vendite"
    )

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeEntryManager = TimeEntryManager(this)

        initViews()
        setupDepartmentSpinner()
        setupRecyclerView()
        setupButtons()
        updateUI()
    }

    private fun initViews() {
        departmentSpinner = findViewById(R.id.departmentSpinner)
        btnClockIn = findViewById(R.id.btnClockIn)
        btnClockOut = findViewById(R.id.btnClockOut)
        btnExport = findViewById(R.id.btnExport)
        recyclerView = findViewById(R.id.recyclerView)
        emptyStateView = findViewById(R.id.tvEmptyState)
    }

    private fun setupDepartmentSpinner() {
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        departmentSpinner.setAdapter(arrayAdapter)
        
        // Set default selection
        if (departmentSpinner.text.isEmpty()) {
            departmentSpinner.setText(departments[0], false)
        }
    }

    private fun setupRecyclerView() {
        adapter = TimeEntryAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButtons() {
        btnClockIn.setOnClickListener {
            handleClockIn()
        }

        btnClockOut.setOnClickListener {
            handleClockOut()
        }

        btnExport.setOnClickListener {
            handleExport()
        }
    }

    private fun handleClockIn() {
        val department = departmentSpinner.text.toString()
        if (department.isEmpty()) {
            Toast.makeText(this, "Seleziona un reparto", Toast.LENGTH_SHORT).show()
            return
        }

        val activeEntry = timeEntryManager.getActiveEntry()
        if (activeEntry != null) {
            Toast.makeText(this, "C'è già una timbratura attiva", Toast.LENGTH_SHORT).show()
            return
        }

        timeEntryManager.clockIn(department)
        Toast.makeText(this, getString(R.string.clocked_in), Toast.LENGTH_SHORT).show()
        updateUI()
    }

    private fun handleClockOut() {
        val activeEntry = timeEntryManager.getActiveEntry()
        if (activeEntry == null) {
            Toast.makeText(this, "Nessuna timbratura attiva", Toast.LENGTH_SHORT).show()
            return
        }

        timeEntryManager.clockOut(activeEntry.id)
        Toast.makeText(this, getString(R.string.clocked_out), Toast.LENGTH_SHORT).show()
        updateUI()
    }

    private fun handleExport() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
                return
            }
        }

        exportToExcel()
    }

    private fun exportToExcel() {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN).format(Date())
        val entries = timeEntryManager.getEntriesForDate(today)

        if (entries.isEmpty()) {
            Toast.makeText(this, "Nessuna timbratura da esportare", Toast.LENGTH_SHORT).show()
            return
        }

        val exporter = ExcelExporter(this)
        val file = exporter.exportToExcel(entries, today)

        if (file != null) {
            Toast.makeText(this, getString(R.string.export_success) + "\n${file.absolutePath}", 
                Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.export_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val entries = timeEntryManager.getTodayEntries()
        
        if (entries.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyStateView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyStateView.visibility = View.GONE
            adapter.updateEntries(entries)
        }

        // Update button states
        val activeEntry = timeEntryManager.getActiveEntry()
        btnClockIn.isEnabled = activeEntry == null
        btnClockOut.isEnabled = activeEntry != null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportToExcel()
            } else {
                Toast.makeText(this, getString(R.string.permission_required), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
