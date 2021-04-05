package com.usm.autocurate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.usm.autocurate.R
import com.usm.autocurate.databinding.ActivityHomeBinding
import com.usm.autocurate.fragments.HomeFragment
import com.usm.autocurate.fragments.MyCurationFragment
import com.usm.autocurate.fragments.SearchFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val mycurationfragment = MyCurationFragment()
    private val searchfragment = SearchFragment()
    private val homefragment = HomeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        replaceFragment(searchfragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_mycuration -> replaceFragment(mycurationfragment)
                R.id.ic_search -> replaceFragment(searchfragment)
                R.id.ic_home -> replaceFragment(homefragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                commit()
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}