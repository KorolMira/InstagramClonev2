package com.example.instagramclonev2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclonev2.R
import com.example.instagramclonev2.adapter.FavoriteAdapter
import com.example.instagramclonev2.adapter.HomeAdapter
import com.example.instagramclonev2.model.Post

class FavoriteFragment : BaseFragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(activity,1))



        refreshAdapter(loadPosts())
    }

    private fun loadPosts(): ArrayList<Post> {

        val items = ArrayList<Post>()
        items.add(Post("https://images.unsplash.com/photo-1649119947511-e56da6f1fa1c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"))
        items.add(Post("https://images.unsplash.com/photo-1647376955675-dbef1657003c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"))
        items.add(Post("https://images.unsplash.com/photo-1592660716763-09efba6db4e3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80"))

        return items

    }

    fun refreshAdapter(items: ArrayList<Post>){
        val adapter = FavoriteAdapter(this,items)
        recyclerView.adapter = adapter
    }
}