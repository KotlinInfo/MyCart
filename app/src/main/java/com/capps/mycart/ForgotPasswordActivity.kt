package com.capps.mycart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capps.mycart.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var  binding: ActivityForgotPasswordBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnResetPassword.setOnClickListener {

              val email = binding.etForgotPassword.text.toString();

                 if(email.isNotEmpty())
                 {
                     firebaseAuth.sendPasswordResetEmail(email)
                         .addOnSuccessListener {
                             Toast.makeText(
                                 this@ForgotPasswordActivity,
                                 "Reset link is Sent on Email Address",
                                 Toast.LENGTH_SHORT
                             ).show()

                         }
                         .addOnFailureListener {
                             Toast.makeText(this@ForgotPasswordActivity, it.toString(), Toast.LENGTH_LONG).show()
                         }

                 }else{


                 }
        }


    }
}