package com.example.instagramclonev2.manager.handler

import com.example.instagramclonev2.model.Post
import com.example.instagramclonev2.model.User
import java.lang.Exception

interface DBPostHandler {
    fun onSuccess(post: Post)
    fun onError(e: Exception)
}