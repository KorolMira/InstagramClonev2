package com.example.instagramclonev2.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.instagramclonev2.R
import com.example.instagramclonev2.manager.AuthManager
import com.example.instagramclonev2.manager.handler.AuthHandler
import com.example.instagramclonev2.utils.Extensions.toast
import java.lang.Exception

/**
 * In SignInActivity, user can login using email, password
 */

class SignInActivity : BaseActivity() {
    val TAG = SignInActivity::class.java.toString()
    lateinit var et_email: EditText
    lateinit var et_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initView()
        setTransparentStatusBar()
    }

    private fun initView() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        val b_sign = findViewById<Button>(R.id.b_signin)
        b_sign.setOnClickListener {
            var email = et_email.text.toString().trim()
            var password = et_password.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseSignIn(email,password)
            }
        }
        val tv_signup = findViewById<TextView>(R.id.tv_signup)
        tv_signup.setOnClickListener { callSignUpActivity() }
    }

    fun firebaseSignIn(email: String, password: String){
        showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler{
            override fun onSuccess(uid: String) {
                dismissLoading()
                toast(getString(R.string.str_signin_success))
                callMainActivity(this@SignInActivity)
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast(getString(R.string.str_signin_failed))
            }

        })
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}