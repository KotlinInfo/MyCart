package com.capps.mycart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        val email = findViewById<EditText>(R.id.etEmail)
        val pass = findViewById<EditText>(R.id.etPass)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonNewRegister = findViewById<Button>(R.id.buttonNewRegister)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        buttonReset.setOnClickListener {
            val email = email.text.toString()
            firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this@LoginActivity, "email reset link sent", Toast.LENGTH_SHORT)
                    .show()

            }.addOnFailureListener {
                Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_SHORT).show()

            }
        }
        buttonNewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            val email = email.text.toString()
            val pass = pass.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(object :
                OnSuccessListener<AuthResult> {
                override fun onSuccess(p0: AuthResult?) {
                    Toast.makeText(this@LoginActivity, "Login success", Toast.LENGTH_SHORT).show()

                }
            }).addOnFailureListener(object : OnFailureListener {
                override fun onFailure(p0: Exception) {
                    Toast.makeText(this@LoginActivity, p0.toString(), Toast.LENGTH_SHORT).show()

                }
            })

        }

    }
}