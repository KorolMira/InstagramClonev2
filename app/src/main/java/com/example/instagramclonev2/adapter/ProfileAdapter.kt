package com.example.instagramclonev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramclonev2.R
import com.example.instagramclonev2.fragment.ProfileFragment
import com.example.instagramclonev2.model.Post
import com.example.instagramclonev2.utils.Utils
import com.google.android.material.imageview.ShapeableImageView

class ProfileAdapter(var fragment: ProfileFragment, var items: ArrayList<Post>): BaseAdapter() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_post_profile, parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post : Post = items[position]
        if (holder is PostViewHolder){
            var iv_post = holder.iv_post
            var tv_caption = holder.tv_caption
            setViewHeight(iv_post)
            tv_caption.text = post.caption
            Glide.with(fragment).load(post.postImg).into(iv_post)
        }
    }

    class PostViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        var iv_post: ShapeableImageView
        var tv_caption: TextView

        init {
            iv_post = view.findViewById(R.id.iv_post)
            tv_caption = view.findViewById(R.id.tv_caption)
        }
    }

    /**
     * Set ShapeableImageView height as a half of screen width
     */
    private fun setViewHeight(view: View) {
        val params: ViewGroup.LayoutParams = view.getLayoutParams()
        params.height = Utils.screenSize(fragment.requireActivity().application).width / 2
        view.setLayoutParams(params)
    }
}