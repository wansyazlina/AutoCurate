package com.usm.autocurate.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.usm.autocurate.databinding.SignupActivityBinding
import com.usm.autocurate.model.User


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: SignupActivityBinding
    lateinit var mAuth: FirebaseAuth
    private var firebaseUserID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mAuth = FirebaseAuth.getInstance()

        binding.createaccountButton.setOnClickListener {
            registerUser()
        }

    }


    private fun registerUser() {
        val username: String = binding.usernameInput.text.toString()
        val email: String = binding.emailInput.text.toString()
        val password: String = binding.passwordInput.text.toString()
        val confirmPassword: String = binding.confirmpasswordInput.text.toString()

        if (username.isEmpty()) {
            Toast.makeText(applicationContext, "Enter your username...", Toast.LENGTH_SHORT).show()
            binding.usernameInput.error = "Username is empty"

        } else if (email.isEmpty()) {
            Toast.makeText(applicationContext, "Enter your email...", Toast.LENGTH_SHORT).show()
            binding.emailInput.error = "Username is empty"

        } else if (password.isEmpty()) {
            Toast.makeText(applicationContext, "Enter your password...", Toast.LENGTH_SHORT).show()
            binding.passwordInput.error = "Password is empty"


        } else if (password != confirmPassword) {
            Toast.makeText(applicationContext, "Your confirm password does not match", Toast.LENGTH_SHORT).show()
            binding.confirmpasswordInput.error = "Confirm Password does not match"


        } else if (confirmPassword.isEmpty()) {
            Toast.makeText(applicationContext, "Enter your confirm password", Toast.LENGTH_SHORT).show()

        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            val user = User(username, email, password)
                            firebaseUserID = mAuth.currentUser!!.uid
                            FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                                    .setValue(user).addOnCompleteListener {
                                        Toast.makeText(applicationContext, "User Registration was successful!", Toast.LENGTH_SHORT).show()
                                    }
                        } else {
                            Toast.makeText(applicationContext, "User Registration Failed.. Try Again", Toast.LENGTH_SHORT).show()

                        }

                    }
        }
    }



}









