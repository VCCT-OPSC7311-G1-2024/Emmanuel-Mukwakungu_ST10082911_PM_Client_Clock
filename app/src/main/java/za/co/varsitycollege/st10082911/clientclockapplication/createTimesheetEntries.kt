package za.co.varsitycollege.st10082911.clientclockapplication

import android.net.Uri


data class createTimesheetEntries (
    val category: String = "",
    val task: String = "",
    val description: String = "",
    val startDate: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val photoUri: Uri? = null
)

