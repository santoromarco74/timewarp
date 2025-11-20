package com.timewarp.app

import android.content.Context
import android.os.Environment
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ExcelExporter(private val context: Context) {

    fun exportToExcel(entries: List<TimeEntry>, date: String): File? {
        try {
            val workbook: Workbook = XSSFWorkbook()
            val sheet: Sheet = workbook.createSheet("Timbrature $date")

            // Create header style
            val headerStyle = workbook.createCellStyle()
            val headerFont = workbook.createFont()
            headerFont.bold = true
            headerStyle.setFont(headerFont)

            // Create header row
            val headerRow: Row = sheet.createRow(0)
            val headers = arrayOf("Reparto", "Orario Entrata", "Orario Uscita", "Ore Lavorate")
            headers.forEachIndexed { index, header ->
                val cell = headerRow.createCell(index)
                cell.setCellValue(header)
                cell.cellStyle = headerStyle
            }

            // Populate data rows
            entries.forEachIndexed { index, entry ->
                val row: Row = sheet.createRow(index + 1)
                row.createCell(0).setCellValue(entry.department)
                row.createCell(1).setCellValue(entry.getFormattedEntryTime())
                row.createCell(2).setCellValue(entry.getFormattedExitTime())
                
                val hoursWorked = entry.getHoursWorked()
                val hoursFormatted = if (hoursWorked > 0) {
                    String.format(Locale.ITALIAN, "%.2f", hoursWorked)
                } else {
                    "In corso"
                }
                row.createCell(3).setCellValue(hoursFormatted)
            }

            // Add total hours row
            if (entries.isNotEmpty()) {
                val totalRow: Row = sheet.createRow(entries.size + 2)
                val totalCell = totalRow.createCell(2)
                totalCell.setCellValue("TOTALE:")
                totalCell.cellStyle = headerStyle
                
                val totalHours = entries.sumOf { it.getHoursWorked() }
                val totalValueCell = totalRow.createCell(3)
                totalValueCell.setCellValue(String.format(Locale.ITALIAN, "%.2f", totalHours))
                totalValueCell.cellStyle = headerStyle
            }

            // Auto-size columns
            for (i in 0 until headers.size) {
                sheet.autoSizeColumn(i)
            }

            // Save to file
            val fileName = "Timbrature_$date.xlsx"
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDir, fileName)
            
            FileOutputStream(file).use { fileOut ->
                workbook.write(fileOut)
            }
            workbook.close()

            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
