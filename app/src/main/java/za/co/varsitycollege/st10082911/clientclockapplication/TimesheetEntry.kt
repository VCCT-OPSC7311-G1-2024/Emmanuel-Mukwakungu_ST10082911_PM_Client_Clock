package za.co.varsitycollege.st10082911.clientclockapplication

data class TimesheetEntry(
    val category: String = "",
    val task: String = "",
    val description: String = "",
    val startDate: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val photoUri: String? = null
)
