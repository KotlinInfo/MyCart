package com.capps.mycart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capps.mycart.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            with(binding) {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Toast.makeText(this@LoginActivity, "Login Successfully", Toast.LENGTH_SHORT)
                            .show()
                        //TODO navigate user on home screen
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()

                    }

            }

        }

        binding.buttonReset.setOnClickListener {

            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@LoginActivity,
                            "Reset link is Sent on Email Address",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    .addOnFailureListener {
                        Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_LONG).show()
                    }
            }


        }

        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }

    }
}