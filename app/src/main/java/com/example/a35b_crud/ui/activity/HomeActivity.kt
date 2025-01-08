package com.example.a35b_crud.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityHomeBinding
import com.example.a35b_crud.ui.fragment.BoringFragment
import com.example.a35b_crud.ui.fragment.HomeFragment
import com.example.a35b_crud.ui.fragment.ProfileFragment
import com.example.a35b_crud.ui.fragment.SettingsFragment
import com.example.a35b_crud.ui.fragment.ThisIsFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome -> replaceFragment(HomeFragment())
                R.id.navProfile -> replaceFragment(ProfileFragment())
                R.id.navSettings -> replaceFragment(SettingsFragment())
                R.id.navThisIs -> replaceFragment(ThisIsFragment())
                R.id.navBoring -> replaceFragment(BoringFragment())
                else -> { }
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager =
            supportFragmentManager

        val fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
}