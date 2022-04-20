package com.example.instagramclonev2.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclonev2.R
import com.example.instagramclonev2.adapter.SearchAdapter
import com.example.instagramclonev2.manager.AuthManager
import com.example.instagramclonev2.manager.DatabaseManager
import com.example.instagramclonev2.manager.DatabaseManager.unFollowUser
import com.example.instagramclonev2.manager.handler.DBFollowHandler
import com.example.instagramclonev2.manager.handler.DBUserHandler
import com.example.instagramclonev2.manager.handler.DBUsersHandler
import com.example.instagramclonev2.model.User
import com.example.instagramclonev2.utils.Extensions
import com.example.instagramclonev2.utils.Utils
import java.lang.Exception

/***
 * In SearchFragment, all registered users can be found by searching keyword and following
 */
class   SearchFragment : BaseFragment() {
    val TAG = SearchFragment::class.java.simpleName
    lateinit var rv_search: RecyclerView
    var items = ArrayList<User>()
    var users = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        rv_search = view.findViewById(R.id.rv_search)
        rv_search.setLayoutManager(GridLayoutManager(activity,1))
        val et_search = view.findViewById<EditText>(R.id.et_search)
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = p0.toString().trim()
                userByKeyword(keyword)
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        loadUser()
    }

    private fun refreshAdapter(items: ArrayList<User>) {
        val adapter = SearchAdapter(this,items)
        rv_search.adapter = adapter
    }

    private fun userByKeyword(keyword: String){
        if(keyword.isEmpty())
            refreshAdapter(items)

        users.clear()
        for (user in items)
            if(user.fullname.toLowerCase().startsWith(keyword.toLowerCase()))
                users.add(user)

        refreshAdapter(users)
    }

    private fun loadUser(){
        val uid = AuthManager.currentUser()!!.uid
        DatabaseManager.loadUsers(object : DBUsersHandler {
            override fun onSuccess(users: ArrayList<User>) {
                DatabaseManager.loadFollowing(uid, object : DBUsersHandler{
                    override fun onSuccess(following: ArrayList<User>) {
                        items.clear()
                        items.addAll(margedUsers(uid, users, following))
                        refreshAdapter(items)
                    }

                    override fun onError(e: Exception) {

                    }

                })

            }
            override fun onError(e: Exception) {

            }
        })
    }

    private fun margedUsers(uid: String, users: ArrayList<User>, following: ArrayList<User>): Collection<User> {
            val items = ArrayList<User>()
        for (u in users){
            val user = u
            for (f in following){
                if (u.uid == f.uid){
                    user.isFollowed = true
                    break
                }
            }
            if (uid != user.uid){
                items.add(user)
            }
        }
        return items
    }


    fun followOrUnfollow(to: User) {
        val uid = AuthManager.currentUser()!!.uid
        if (!to.isFollowed) {
            followUser(uid, to)
        } else {
            unFollowUser(uid, to)
        }
    }

    private fun followUser(uid: String, to: User) {
        DatabaseManager.loadUser(uid, object : DBUserHandler {
            override fun onSuccess(me: User?) {
                DatabaseManager.followUser(me!!, to, object : DBFollowHandler {
                    override fun onSuccess(isFollowed: Boolean) {
                        to.isFollowed = isFollowed
                        DatabaseManager.storePostsToMyFeed(uid, to)
                        Utils.sendNote(requireContext(), me, to.device_token)
                    }

                    override fun onError(e: Exception) {

                    }
                })
            }

            override fun onError(e: Exception) {

            }
        })
    }

    private fun unFollowUser(uid: String, to: User){
        DatabaseManager.loadUser(uid, object : DBUserHandler{
            override fun onSuccess(me: User?) {
                DatabaseManager.unFollowUser(me!!, to , object : DBFollowHandler{
                    override fun onSuccess(isFollowed: Boolean) {
                        to.isFollowed = false
                        DatabaseManager.removePostsFromMyFeed(uid, to)
                    }

                    override fun onError(e: Exception) {

                    }

                })
            }

            override fun onError(e: Exception) {

            }

        })
    }
}