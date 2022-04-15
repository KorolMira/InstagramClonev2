package com.example.instagramclonev2.manager.handler

import com.example.instagramclonev2.model.User
import java.lang.Exception

interface DBUsersHandler {
    fun onSuccess(user: ArrayList<User>)
    fun onError(e: Exception)
}