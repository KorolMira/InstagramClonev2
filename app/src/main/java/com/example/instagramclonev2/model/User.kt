package com.example.instagramclonev2.model

class User {
    var fullname: String = ""
    var email: String = ""
    var uid: String = ""
    var password: String = ""
    var userImg: String = ""

    var device_id = ""
    var device_type = "A"
    var device_token = ""

    var isFollowed: Boolean = false

    constructor(fullname: String, email: String) {
        this.email = email
        this.fullname = fullname
    }

    constructor(fullname: String, email: String, userImg: String){
        this.email = email
        this.fullname = fullname
        this.userImg = userImg
    }

    constructor(fullname: String, email: String, password: String, userImg: String){
        this.email = email
        this.fullname = fullname
        this.userImg = userImg
        this.password = password
    }

    override fun toString(): String {
        return "User(uid='$uid', fullname='$fullname', email='$email', password='$password', userImg='$userImg', isFollowed=$isFollowed, device_id='$device_id', device_type='$device_type', device_token='$device_token')"
    }
}