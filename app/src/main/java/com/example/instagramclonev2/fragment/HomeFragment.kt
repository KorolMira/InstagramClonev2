package com.example.instagramclonev2.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclonev2.R
import com.example.instagramclonev2.adapter.HomeAdapter
import com.example.instagramclonev2.manager.AuthManager
import com.example.instagramclonev2.manager.DatabaseManager
import com.example.instagramclonev2.manager.handler.DBPostsHandler
import com.example.instagramclonev2.model.Post
import java.lang.Exception
import java.lang.RuntimeException

class HomeFragment : BaseFragment() {
    val TAG = HomeFragment::class.java.simpleName
    lateinit var recyclerView: RecyclerView
    private var listener: HomeListener? = null
    var feeds = ArrayList<Post>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initView(view)
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser && feeds.size > 0){
            loadMyFeeds()
        }
    }

    /**
     * onAttach is for communication of Fragment
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is HomeFragment.HomeListener){
            context
        }else{
            throw RuntimeException("$context must implement HomeListener")
        }
    }

    /**
     * onDetach is for communication of Fragment
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(activity,1))

        val iv_camera = view.findViewById<ImageView>(R.id.iv_camera)
        iv_camera.setOnClickListener { listener!!.scrollToUpload() }

        loadMyFeeds()
    }

    private fun loadMyFeeds() {
        showLoading(requireActivity())
        val uid = AuthManager.currentUser()!!.uid
        DatabaseManager.loadFeeds(uid, object : DBPostsHandler{
            override fun onSuccess(posts: ArrayList<Post>) {
                dismissLoading()
                feeds.clear()
                feeds.addAll(posts)
                refreshAdapter(feeds)
            }

            override fun onError(e: Exception) {
                dismissLoading()
            }

        })
    }


    fun refreshAdapter(items: ArrayList<Post>){
        val adapter = HomeAdapter(this,items)
        recyclerView.adapter = adapter
    }

    /**
     * This interface is created for communication with UploadFragment
     */
    interface HomeListener{
        fun scrollToUpload()
    }
}