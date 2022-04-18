package com.example.instagramclonev2.model

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class Post {
    var caption : String = ""
    var postImg: String = ""
    var id : String = ""
    var currentDate: String = ""

    var uid : String = ""
    var fullname: String = ""
    var userImg: String = ""

    var isLiked: Boolean = false

    constructor(postImg: String){
        this.postImg = postImg
    }

    constructor(caption: String, postImg: String){
        this.caption = caption
        this.postImg = postImg
    }

    constructor(id: String, caption: String, postImg: String){
        this.id = id
        this.caption = caption
        this.postImg = postImg
    }

    open fun setCurrentTime(){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        currentDate = sdf.format(Date())
    }

}