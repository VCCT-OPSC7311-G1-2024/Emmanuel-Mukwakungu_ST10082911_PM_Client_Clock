package za.co.varsitycollege.st10082911.clientclockapplication

import java.text.SimpleDateFormat
import java.util.Locale

class TimeSheetManager(private val entries: List<TimesheetEntry>) {

    fun getTasksByDateRange(startDate: String, endDate: String): List<TimesheetEntry> {
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val start = sdf.parse(startDate) ?: return emptyList()
        val end = sdf.parse(endDate) ?: return emptyList()

        return entries.filter {
            val taskDate = sdf.parse(it.startDate)
            taskDate != null && !taskDate.before(start) && !taskDate.after(end)
        }
    }
}

