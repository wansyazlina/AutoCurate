package com.usm.autocurate.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.usm.autocurate.R
import com.usm.autocurate.databinding.FragmentHomeBinding
import com.usm.autocurate.view.LoginRegisterActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var fragmentFirstBinding: FragmentHomeBinding? = null
    private var mFirebaseDatabase: DatabaseReference?=null
    private var mFirebaseInstance: FirebaseDatabase?=null
    var userId:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        fragmentFirstBinding = binding

        //initialize popup menu
        val popupMenu = PopupMenu(activity, binding.hamburgermenu)
        popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(activity, LoginRegisterActivity::class.java);
                    startActivity(intent);
                    Toast.makeText(activity, "You are logged out!", Toast.LENGTH_LONG).show()
                }
            }
            true
        }
        binding.hamburgermenu.setOnClickListener() {
            popupMenu.show()
        }

        binding.progressBarHomeload.visibility= View.VISIBLE

        //get reference to 'users' node
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Users")

        val user = FirebaseAuth.getInstance().currentUser!!.uid
        //add it only if it is not saved to database
//        if (user != null) {
//            userId = user.uid
//
//        }

        Thread {
            try {
                val postListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Get Post object and use the values to update the UI
                        val userInstance = dataSnapshot.child("userName").getValue(String::class.java)
                        if (userInstance != null) {
                            Log.i("NAME: ", userInstance)
                            binding.nameText.text = userInstance
                        }
                        binding.progressBarHomeload.visibility= View.INVISIBLE
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                    }
                }
                mFirebaseDatabase!!.child(user).addValueEventListener(postListener)

            } catch (ignored: Exception) {
            }
        }.start()




    }



    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentFirstBinding = null
        super.onDestroyView()
    }











    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}