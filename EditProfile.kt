package com.rohakabir.i200552

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

import com.rohakabir.i200552.databinding.ActivityEditProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var password:String






    private var imageUri : Uri? = null
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
        binding.editdp.setImageURI(imageUri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityEditProfileBinding.inflate(layoutInflater) // Inflate the layout using view binding
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.editdp.setOnClickListener {

            selectImage.launch("image/*")

        }


        getUserData()

        binding.updatebtn.setOnClickListener {

            val username: String = binding.editname.getText().toString()
            val useremail: String = binding.editemail.getText().toString()
            val contactnumber: String = binding.editnumber.getText().toString()
            val country: String = binding.editcountry.getText().toString()
            val city: String = binding.editcity.getText().toString()

            updateData(username, useremail, contactnumber, country, city)

        }






    }

    private fun updateData(username: String, useremail: String, contactnumber: String, country: String, city: String) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val user = HashMap<String, Any>()
        user["username"] = username
        user["useremail"] = useremail
        user["contactnumber"] = contactnumber
        user["country"] = country
        user["city"] = city
        if (userId != null) {
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users")
            databaseReference.child(userId).updateChildren(user).addOnCompleteListener {task ->
                if (task.isSuccessful) {

                    firebaseAuth.currentUser?.verifyBeforeUpdateEmail(useremail)
                        ?.addOnCompleteListener { emailUpdateTask ->
                            if (emailUpdateTask.isSuccessful) {
                                // Email update successful
                                binding.editname.setText("")
                                binding.editemail.setText("")
                                binding.editnumber.setText("")
                                binding.editcountry.setText("")
                                binding.editcity.setText("")
                                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                // Email update failed
                                val errorMessage = emailUpdateTask.exception?.message
                                Log.e("EditProfile", "Failed to Update Email: $errorMessage")
                                Toast.makeText(this, "$errorMessage\",", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }else {
                    // Handle failure
                    Toast.makeText(this, "Failed to Update" , Toast.LENGTH_SHORT).show()
                }

            }
        }





    }

    private fun getUserData() {

        /* val currentUser = firebaseAuth.currentUser
        currentUser?.let { user ->
            val userId = user.uid
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val documentSnapshot =

                        firestore.collection("users").document(userId).get().await()
                    val userData = documentSnapshot.toObject(Userdata::class.java)

                    // Update UI on the main thread
                    withContext(Dispatchers.Main) {
                        userData?.let { user ->
                            binding.editname.setText(user.username)
                            binding.editemail.setText(user.useremail)
                            binding.editnumber.setText(user.contactnumber)
                            binding.editcountry.setText(user.country)
                            binding.editcity.setText(user.city)


                            // Set other fields as needed
                        }
                    }
                } catch (e: Exception) {
                    // Handle exception
                }

            }
        }*/


        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)

            databaseReference.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    if(task.getResult().exists()){

                        val dataSnapshot = task.getResult()
                        val name: String? = dataSnapshot.child("username").getValue(String::class.java)
                        val email: String? = dataSnapshot.child("useremail").getValue(String::class.java)
                        val number: String? = dataSnapshot.child("contactnumber").getValue(String::class.java)

                        val country: String? = dataSnapshot.child("country").getValue(String::class.java)
                        val city: String? = dataSnapshot.child("city").getValue(String::class.java)
                        password = dataSnapshot.child("password").getValue(String::class.java) ?: ""


                        binding.editcity.setText(city)
                        binding.editname.setText(name)
                        binding.editnumber.setText(number)
                        binding.editcountry.setText(country)
                        binding.editemail.setText(email)


                    }else{

                        Toast.makeText(this, "User does not exists" , Toast.LENGTH_SHORT).show()
                    }


                    // Handle data retrieval success
                } else {
                    // Handle data retrieval failure
                    Toast.makeText(this, "Data retrieval failed" , Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            // Handle the case where currentUser is null
        }


    }



}