package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val categoryButton: CardView = findViewById(R.id.categoryCard)
        categoryButton.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }

        val goalButton: CardView = findViewById(R.id.goaslsCard)
        goalButton.setOnClickListener {
            val intent = Intent(this, AddGoals::class.java)
            startActivity(intent)
        }

        val manageTimesheetButton: CardView = findViewById(R.id.manageSheetCard)
        manageTimesheetButton.setOnClickListener {
            val intent = Intent(this, DisplayTimesheet::class.java)
            startActivity(intent)
        }


        val dailWorkFeatureButton: CardView = findViewById(R.id.dailyWorkGoalCard)
        dailWorkFeatureButton.setOnClickListener {
            val intent = Intent(this, DailWorkFeature::class.java)
            startActivity(intent)
        }


        // Add the logout functionality with a confirmation dialog
        val logoutButton: CardView = findViewById(R.id.LogoutCard)
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        val graphButton: CardView = findViewById(R.id.reportCard)
       graphButton.setOnClickListener {
            val intent = Intent(this, CreateGraph::class.java)
            startActivity(intent)
        }

    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Finish this activity so the user cannot return to it by pressing back
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}
