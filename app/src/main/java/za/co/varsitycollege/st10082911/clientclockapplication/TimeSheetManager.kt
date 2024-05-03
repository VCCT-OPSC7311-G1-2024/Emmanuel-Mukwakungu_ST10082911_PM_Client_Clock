package za.co.varsitycollege.st10082911.clientclockapplication

import java.text.SimpleDateFormat
import java.util.Locale

class TimeSheetManager(private val entries: List<TimesheetEntry>) {

    fun getTasksByDateRange(startDate: String, endDate: String): List<TimesheetEntry> {
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val start = sdf.parse(startDate)
        val end = sdf.parse(endDate)
        println("Parsed Start Date: $start, End Date: $end")
        return entries.filter {
            val taskDate = sdf.parse(it.startDate)
            taskDate != null && taskDate in start..end
        }
    }
}
