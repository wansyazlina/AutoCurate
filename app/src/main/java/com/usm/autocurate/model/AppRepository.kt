package com.usm.autocurate.model

import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AppRepository( applica: Application) {

    var application: Application = applica
    private lateinit var auth: FirebaseAuth
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>


//    fun register (email: String, password: String ) {
//
//        // Initialize Firebase Auth
//        auth = Firebase.auth
//
////        auth.createUserWithEmailAndPassword(email, password)
////                .addOnCompleteListener(application.getMainExecutor()) { task ->
////                    if (task.isSuccessful) {
////                        // Sign in success, update UI with the signed-in user's information
////                        Log.d(TAG, "createUserWithEmail:success")
////                        val user = auth.currentUser
////                        //updateUI(user)
////                    } else {
////                        // If sign in fails, display a message to the user.
////                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
////
////                        Toast.makeText(application, "Authentication failed.", Toast.LENGTH_SHORT).show()
////                        //updateUI(null)
////                    }
////                }
//
//    }

//    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser> {
//        return userMutableLiveData
//    }


}






