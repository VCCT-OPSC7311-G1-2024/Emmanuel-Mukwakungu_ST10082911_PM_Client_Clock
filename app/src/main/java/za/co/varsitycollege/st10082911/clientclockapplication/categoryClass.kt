package za.co.varsitycollege.st10082911.clientclockapplication

data class categoryClass(
    val name: String,
    val timesheetEntries: ArrayList<createTimesheetEntries> = ArrayList()
)