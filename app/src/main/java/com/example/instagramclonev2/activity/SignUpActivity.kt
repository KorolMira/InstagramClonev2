package com.example.instagramclonev2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.instagramclonev2.R
import com.example.instagramclonev2.manager.AuthManager
import com.example.instagramclonev2.manager.DatabaseManager
import com.example.instagramclonev2.manager.handler.AuthHandler
import com.example.instagramclonev2.manager.handler.DBUserHandler
import com.example.instagramclonev2.model.User
import com.example.instagramclonev2.utils.Extensions.toast
import com.example.instagramclonev2.utils.Logger.d
import com.google.firebase.firestore.util.Logger
import java.lang.Exception

/**
 * In SignUpActivity, user can signup using fullname, email,password
 */
class SignUpActivity : BaseActivity() {
    val TAG = SignUpActivity::class.java.toString()
    lateinit var et_fullname: EditText
    lateinit var et_password: EditText
    lateinit var et_email: EditText
    lateinit var et_cpassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initView()
        setTransparentStatusBar()
    }

    private fun initView() {
        et_cpassword = findViewById(R.id.et_cpassword)
        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        et_fullname = findViewById(R.id.et_fullname)

        val b_signup = findViewById<Button>(R.id.b_signup)
        b_signup.setOnClickListener {
            val cpassword = et_cpassword.text.toString().trim()
            val password = et_password.text.toString().trim()
            val email = et_email.text.toString().trim()
            val fullname = et_fullname.text.toString().trim()

            if(cpassword.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && fullname.isNotEmpty()){
                val user = User(fullname,email,password,"")
                firebaseSignUp(user)

            }
        }
        val tv_signin = findViewById<TextView>(R.id.tv_signin)
        tv_signin.setOnClickListener { finish() }
    }

    fun firebaseSignUp(user: User){
        showLoading(this)
        AuthManager.signUp(user.email, user.password, object : AuthHandler{
            override fun onSuccess(uid: String) {
                user.uid = uid
                storeUserToDB(user)
                dismissLoading()
                toast(getString(R.string.str_signup_success))
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast(getString(R.string.str_signup_failed))
            }

        })
    }

    private fun storeUserToDB(user: User) {
        DatabaseManager.storeUser(user, object : DBUserHandler{
            override fun onSuccess(user: User?) {
                dismissLoading()
                callMainActivity(this@SignUpActivity)
            }

            override fun onError(e: Exception) {

            }

        })
    }
}