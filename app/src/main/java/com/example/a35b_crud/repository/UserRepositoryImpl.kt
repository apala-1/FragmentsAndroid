package com.example.a35b_crud.repository

import android.widget.Toast
import com.example.a35b_crud.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl : UserRepository {

    var auth: FirebaseAuth= FirebaseAuth.getInstance()

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference.
    child("users")

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Login Successful")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }

    override fun signup(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                val userId = auth.currentUser?.uid.toString()
                callback(true, "SignUp Successful", userId)
            }else{
                callback(false,it.exception?.message.toString(), "")
            }
        }
    }

    override fun forgotPassword(email: String, callback: (Boolean, String) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true, "Password Reset link sent to $email")
            }
            else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun addUserToDatabase(
        userId: String,
        userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        reference.child(userId.toString()).setValue(userModel)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    callback(true,"Registered successfully")
                }else{
                    callback(false, it.exception?.message.toString())
                }
            }
    }

    override fun getCurrentUser(): FirebaseUser? {
       return  auth.currentUser
    }
}