package com.example.a35b_crud.repository

import com.example.a35b_crud.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {


    fun login(email: String, password: String,callback:(Boolean, String) -> Unit)

    fun signup(email: String, password: String, callback: (Boolean, String, String) -> Unit)

    fun forgotPassword(email: String, callback: (Boolean, String) -> Unit)

    fun addUserToDatabase(userId: String ,userModel: UserModel, callback: (Boolean, String) -> Unit)

    fun getCurrentUser() : FirebaseUser?
}