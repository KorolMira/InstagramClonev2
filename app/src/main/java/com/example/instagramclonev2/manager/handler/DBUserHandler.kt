package com.example.instagramclonev2.manager.handler

import com.example.instagramclonev2.model.User
import java.lang.Exception

interface DBUserHandler {
    fun onSuccess(user: User? = null)
    fun onError(e: Exception)
}