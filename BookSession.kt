package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.rohakabir.i200552.databinding.ActivityBookSessionBinding

class BookSession : AppCompatActivity() {
    private lateinit var binding: ActivityBookSessionBinding
    private lateinit var selectedTime:String
    private lateinit var selectedDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

             selectedDate = "$dayOfMonth/${month + 1}/$year"

            // Store selected date in Firebase Realtime Database

        }

        binding.button10.setOnClickListener {

             selectedTime = "10:00AM"

        }

        binding.button11.setOnClickListener {

             selectedTime = "11:00AM"


        }


        binding.button12.setOnClickListener {

            selectedTime = "12:00PM"


        }

        binding.bookingbtn.setOnClickListener {

            storeSelectedDate(selectedDate)
            setTime(selectedTime)

        }


    }

    private fun setTime(selectedTime: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid



        if(userId!=null) {
            val timeRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                .child("appointments")


            // Push the selected date to Firebase database
            timeRef.push().setValue(selectedTime)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Time added to database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Failed to add time to database",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }


    }

    private fun storeSelectedDate(selectedDate: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid



        if(userId!=null) {
            val datesRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                .child("appointments")


            // Push the selected date to Firebase database
            datesRef.push().setValue(selectedDate)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Date added to database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Failed to add date to database",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }



    fun navigateToNextActivity(view: View) {
     val intent = Intent(this, AddMentor::class.java)
        startActivity(intent)

    }
}
