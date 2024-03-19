package com.rohakabir.i200552


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import android.net.Uri
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.rohakabir.i200552.databinding.ActivityAddMentorBinding
import com.rohakabir.i200552.utils.Config
import com.rohakabir.i200552.utils.Config.hideDialog

class AddMentor : AppCompatActivity() {

    private lateinit var binding: ActivityAddMentorBinding

    private var imageUri : Uri? = null
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
       binding.cameraclick.setImageURI(imageUri)
    }



    // val name = findViewById<EditText>(R.id.nameinput)
    //  val desc = findViewById<EditText>(R.id.Descinput)
    //val sessionPrice = findViewById<EditText>(R.id.sessionprice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMentorBinding.inflate(layoutInflater) // Inflate the layout using view binding
        setContentView(binding.root)

        /*  val availability = listOf("Available", "Not Available")
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.addmentor_dropdown)
        val adapterItems = ArrayAdapter(this, R.layout.dropdown, availability)

        autoCompleteTextView.setAdapter(adapterItems)


        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener{
                adapterview, view, i, l ->

            val item = adapterview.getItemAtPosition(i)
            Toast.makeText(this, "Status: $item" , Toast.LENGTH_SHORT).show()


        }*/

        // val btnupload = findViewById<Button>(R.id.button_upload)


        binding.cameraclick.setOnClickListener {

            selectImage.launch("image/*")

        }

       binding.buttonupload.setOnClickListener {
            validateData()
            val intent = Intent(this, Photo::class.java)
            startActivity(intent)
        }

    }

   private fun validateData() {


        if(binding.nameinput.text.toString().isEmpty() ||
            binding.Descinput.text.toString().isEmpty() ||
            binding.sessionprice.text.toString().isEmpty()){

            Toast.makeText(this, "Please enter all fields" , Toast.LENGTH_SHORT).show()
        }else{
            uploadImage()
        }
    }


    private fun uploadImage() {
        Config.showDialog(this)

        val storageRef = FirebaseStorage.getInstance().getReference("mentorProfiles")
            .child("profile.jpg")


            storageRef.putFile(imageUri!!)
            .addOnSuccessListener {

                storageRef.downloadUrl.addOnSuccessListener {
                    storeData(it)
                }.addOnFailureListener{
                    hideDialog()
                    Toast.makeText(this, "Can't store" , Toast.LENGTH_SHORT).show()

                }

            }.addOnFailureListener {
                    hideDialog()
                    Toast.makeText(this, "Ca't store 2", Toast.LENGTH_SHORT).show()
                }

    }

   private fun storeData(imageUrl: Uri?) {

        val data = Mentormodel(
                    name = binding.nameinput.text.toString(),
                    description = binding.Descinput.text.toString(),
                    SessionPrice = binding.sessionprice.text.toString(),
                    Image = imageUrl.toString()

        )

        FirebaseDatabase.getInstance().getReference("mentors")
            .setValue(data).addOnCompleteListener{

                hideDialog()
                if(it.isSuccessful){
                    Toast.makeText(this, "Mentor added successfully" , Toast.LENGTH_SHORT).show()

                }else{

                    Toast.makeText(this, it.exception!!.message , Toast.LENGTH_SHORT).show()

                }
            }


    }








}