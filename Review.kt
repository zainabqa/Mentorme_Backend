package com.rohakabir.i200552

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rohakabir.i200552.databinding.ActivityReviewBinding

class Review : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ratingbar.rating = 2.5f
        binding.ratingbar.stepSize = 1f

        binding.ratingbar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            Toast.makeText(this, "rated successfully" , Toast.LENGTH_SHORT).show()
        }


    }
}