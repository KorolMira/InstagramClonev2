package com.example.instagramclonev2.utils

import android.app.Activity
import android.widget.Toast
import kotlin.time.minutes

object Extensions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}