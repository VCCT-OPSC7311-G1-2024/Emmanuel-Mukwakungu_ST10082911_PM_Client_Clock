package za.co.varsitycollege.st10082911.clientclockapplication

data class CategoryClass(
    val name: String,
    val timesheetEntries: MutableList<TimesheetEntry> = mutableListOf()
)
