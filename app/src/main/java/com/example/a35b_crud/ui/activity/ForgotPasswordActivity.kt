package com.example.a35b_crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityForgotPasswordBinding
import com.example.a35b_crud.utils.LoadingUtils
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    lateinit var loadingUtils: LoadingUtils
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils = LoadingUtils(this)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.backLogin.setOnClickListener {
            val intent = Intent(
                this@ForgotPasswordActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }

        binding.btnReset.setOnClickListener{
            val email = binding.editEmail.text.toString()

        if (email.isNotEmpty()){
            loadingUtils.show()
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    loadingUtils.dismiss()
                    if (task.isSuccessful){
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Reset Link Sent",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(
                this@ForgotPasswordActivity,
                "Please fill all the fields",
                Toast.LENGTH_SHORT
            ).show()
        }


        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}