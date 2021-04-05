package com.usm.autocurate.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.usm.autocurate.databinding.LoginActivityBinding

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: LoginActivityBinding

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
            finish()
        }
        else {
            Toast.makeText(applicationContext,"You Have not Signed In Yet...", Toast.LENGTH_SHORT).show() }

        //USER LOGIN

        binding.signinButton.setOnClickListener {
            userLogin()
        }

        //USER SIGNUP OR REGISTER

        binding.signupButton.setOnClickListener() {
            Toast.makeText(applicationContext,"REGISTER", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this, RegisterActivity::class.java)
            intent2.putExtra("key", "UserRegistering")
            startActivity(intent2)

        }
    }

    private fun userLogin(){

        val email : String = binding.inputEmail.text.toString()
        val password: String = binding.inputPassword.text.toString()

        if(email.isEmpty()){
            binding.inputEmail.error = "Email is required"
            binding.inputEmail.requestFocus()
            return
        }

//
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            binding.inputEmail.error = "Email is not valid"
//            binding.inputEmail.requestFocus()
//            return
//
//        }

        if(password.isEmpty()){
            binding.inputPassword.error = "Password is required"
            binding.inputPassword.requestFocus()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this ){ task ->
            if(task.isSuccessful){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("key", "UserLoggedIn")
                startActivity(intent)
                finish()

            }
            else {
                Toast.makeText(applicationContext,"Failed to check in , Check your credentials", Toast.LENGTH_LONG).show()
            }

        }

    }

}