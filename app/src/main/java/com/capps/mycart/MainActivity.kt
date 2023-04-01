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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<EditText>(R.id.etName)
        val mobile = findViewById<EditText>(R.id.etMobile)
        val email = findViewById<EditText>(R.id.etEmail)
        val pass = findViewById<EditText>(R.id.etPass)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        buttonRegister.setOnClickListener {
            val name = name.text.toString()
            val mobile = mobile.text.toString()
            val email = email.text.toString()
            val pass = pass.text.toString()

            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(object : OnSuccessListener<AuthResult> {
                    override fun onSuccess(p0: AuthResult?) {
                        Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()

                        val db = Firebase.firestore
                        p0?.user?.uid?.let { it1 ->
                            db.collection("UserDB").document(it1).set(User(name, mobile, email))
                        }

                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))

                    }

                }).addOnFailureListener(object : OnFailureListener {
                    override fun onFailure(p0: Exception) {
                        Toast.makeText(this@MainActivity, p0.toString(), Toast.LENGTH_SHORT).show()
                    }
                })


        }


    }

    data class User(val name: String, val mobile: String, val email: String)
}

