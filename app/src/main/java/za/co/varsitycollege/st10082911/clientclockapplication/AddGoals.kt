package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

val goalEntries: ArrayList<CreateGoals> = ArrayList()
class AddGoals : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtViewGoalName: TextView
    private lateinit var txtViewMinGoal: TextView
    private lateinit var txtViewMaxGoal: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoals)

        //finding ids for the textviews
        txtViewGoalName = findViewById<TextView>(R.id.txtGoalName)
        txtViewMinGoal = findViewById<TextView>(R.id.txtMinGoal)
        txtViewMaxGoal = findViewById<TextView>(R.id.txtMaxGoal)

        val addGoalsButton: Button = findViewById(R.id.buttonAddGoal)
        addGoalsButton.setOnClickListener {
            val intent = Intent(this, CreateGoals::class.java)
            startActivity(intent)
            finish()

        }

        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onClick(view: View) {
        val intent = Intent(this, CreateGoals::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val st1 = data?.getStringExtra("name")
                val st2 = data?.getStringExtra("min")
                val st3 = data?.getStringExtra("max")

                txtViewGoalName.setText(st1)
                txtViewMinGoal.setText(st2)
                txtViewMaxGoal.setText(st3)

                val goalEntry = CreateGoals()

                goalEntries.clear()
                goalEntries.add(goalEntry)
            }
        }
    }
}
