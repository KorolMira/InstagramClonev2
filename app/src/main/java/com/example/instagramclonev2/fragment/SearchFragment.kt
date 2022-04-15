package com.example.instagramclonev2.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclonev2.R
import com.example.instagramclonev2.adapter.SearchAdapter
import com.example.instagramclonev2.manager.DatabaseManager
import com.example.instagramclonev2.manager.handler.DBUsersHandler
import com.example.instagramclonev2.model.User
import java.lang.Exception

/***
 * In SearchFragment, all registered users can be found by searching keyword and following
 */
class SearchFragment : BaseFragment() {
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
        refreshAdapter(loadUsers())
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
        DatabaseManager.loadUsers(object : DBUsersHandler {

            override fun onSuccess(users: ArrayList<User>) {
                items.clear()
                items.addAll(users)
                refreshAdapter(items)
            }

            override fun onError(e: Exception) {

            }
        })
    }

    private fun loadUsers() : ArrayList<User>{
        items = ArrayList<User>()
        items.add(User("Islombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Muslimbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Usmonbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Ikrombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Islombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Muslimbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Usmonbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Ikrombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Islombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Muslimbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Usmonbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Ikrombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Islombek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Muslimbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Usmonbek","nasriddinov.islom.19@gmail.com"))
        items.add(User("Ikrombek","nasriddinov.islom.19@gmail.com"))

        return items

    }
}