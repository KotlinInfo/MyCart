package com.capps.mycart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capps.mycart.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {

            with(binding) {
                val name = etName.text.toString()
                val mobile = etMobile.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                val firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val uid = it.user?.uid

                        //store data in firestore
                        val db = Firebase.firestore
                        if (uid != null) {
                            db.collection("UserDB").document(uid).set(User(name, mobile, email))
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Register Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    startActivity(
                                        Intent(
                                            this@RegisterActivity,
                                            LoginActivity::class.java
                                        )
                                    )

                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        it.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                        }
                    }.addOnFailureListener {
                        Toast.makeText(this@RegisterActivity, it.toString(), Toast.LENGTH_LONG)
                            .show()
                    }

            }

        }

    }

    data class User(val name: String, val mobile: String, val email: String)
}

