package com.capps.mycart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capps.mycart.databinding.ActivityHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= Firebase.firestore
        db.collection("Shopping Icons")
            .document("Main Category")
            .get().addOnSuccessListener {
                val picasso=Picasso.Builder(this@HomeActivity).build()

                for(i in it.data?.entries!!){
                  val imageUrl=i.value as String

                  binding.textView.text=i.key

                  picasso.load(imageUrl).into(binding.imageView)

              }



        }

    }
}