package za.co.varsitycollege.st10082911.clientclockapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimesheetAdapter(private var entries: List<TimesheetEntry>) : RecyclerView.Adapter<TimesheetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryTextView: TextView = view.findViewById(R.id.categoryTextView)
        val taskTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val startDateTextView: TextView = view.findViewById(R.id.startDateTextView)
        val startTimeTextView: TextView = view.findViewById(R.id.startTimeTextView)
        val endTimeTextView: TextView = view.findViewById(R.id.endTimeTextView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.timesheet_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.categoryTextView.text = entry.category
        holder.taskTextView.text = entry.task
        holder.descriptionTextView.text = entry.description
        holder.startDateTextView.text = entry.startDate
        holder.startTimeTextView.text = entry.startTime
        holder.endTimeTextView.text = entry.endTime
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateEntries(newEntries: List<TimesheetEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = entries.size
}
