package com.example.instagramclonev2.model

class User {
    var fullname: String = ""
    var email: String = ""
    var uid: String = ""
    var password: String = ""
    var userImg: String = ""

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
}