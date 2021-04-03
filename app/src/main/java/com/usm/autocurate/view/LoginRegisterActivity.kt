package com.usm.autocurate.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.usm.autocurate.R
import com.usm.autocurate.databinding.LoginActivityBinding

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: LoginActivityBinding //error

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "userSignedIn")
            }
            startActivity(intent)
        }
        else {
            Toast.makeText(applicationContext,"You Have not Signed In Yet...", Toast.LENGTH_SHORT).show()

        }


        binding.signupButton.setOnClickListener() {
            Toast.makeText(applicationContext,"REGISTER", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this, RegisterActivity::class.java)
            intent2.putExtra("key", "UserRegistering")
            startActivity(intent2)

        }
    }

}