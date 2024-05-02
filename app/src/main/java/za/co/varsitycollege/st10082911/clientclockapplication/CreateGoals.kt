package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CreateGoals(st1: String?, st2: String?, st3: String?) : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtViewGoal: TextView
    private lateinit var txtViewMin: TextView
    private lateinit var txtViewMax: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_goals)


        txtViewGoal = findViewById(R.id.txt_goalName) as TextView
        txtViewMin = findViewById(R.id.txt_goalName) as TextView
        txtViewMax = findViewById(R.id.txt_goalName) as TextView


        val backButton: ImageButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            val intent = Intent(this, AddGoals::class.java)
            startActivity(intent)
            finish()
        }


    }
    override fun onClick(v: View?) {
        val name: String = txtViewGoal.toString()
        val min: String = txtViewMin.toString()
        val max: String = txtViewMax.toString()

        if (txtViewGoal.text.isNotEmpty() || txtViewMin.text.isNotEmpty() || txtViewMax.text.isNotEmpty()) {
            intent.putExtra("name", name)
            intent.putExtra("min", min)
            intent.putExtra("max", max)

            setResult(RESULT_OK, intent)
            finish()
        } else {
            // TextView is empty
        }

    }
}
