package za.co.varsitycollege.st10082911.clientclockapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import za.co.varsitycollege.st10082911.clientclockapplication.databinding.ActivitySignupBinding

class activity_signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnSignUp.setOnClickListener {
            val email = binding.editSignUpEmail.text.toString()
            val password = binding.editSignUpPassword.text.toString()
            val confirmPassword = binding.editConfirmPassword.text.toString()
            val firstName = binding.editFirstname.text.toString()
            val surname = binding.editSurname.text.toString()

            if (password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userMap = hashMapOf(
                                "firstName" to firstName,
                                "surname" to surname,
                                "email" to email
                            )
                            user?.let {
                                db.collection("users").document(it.uid).set(userMap)
                                    .addOnSuccessListener {
                                        Toast.makeText(baseContext, "Sign Up Successful.", Toast.LENGTH_SHORT).show()
                                        // Redirect to Homepage activity
                                        val intent = Intent(this, Homepage::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(baseContext, "Error adding document: $e", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {
                            // Check if the exception is FirebaseAuthUserCollisionException
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                Toast.makeText(baseContext, "This user email already exists.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(baseContext, "Sign Up Failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            } else {
                Toast.makeText(baseContext, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}