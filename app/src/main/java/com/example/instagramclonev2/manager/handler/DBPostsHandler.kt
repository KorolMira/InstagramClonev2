package com.example.instagramclonev2.manager.handler

import com.example.instagramclonev2.model.Post
import com.example.instagramclonev2.model.User
import java.lang.Exception

interface DBPostsHandler {
    fun onSuccess(posts: ArrayList<Post>)
    fun onError(e: Exception)
}