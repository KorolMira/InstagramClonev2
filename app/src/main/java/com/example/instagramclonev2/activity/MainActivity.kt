package com.example.instagramclonev2.activity

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.instagramclonev2.R
import com.example.instagramclonev2.adapter.ViewPagerAdapter
import com.example.instagramclonev2.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(), HomeFragment.HomeListener, UploadFragment.UploadListener {
    val TAG = MainActivity::class.java.toString()
    var index = 0
    lateinit var homeFragment: HomeFragment
    lateinit var uploadFragment: UploadFragment
    lateinit var viewPager: ViewPager
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setColorStatusBar()
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun scrollToUpload(){
        index = 2
        scrollByIndex(index)
    }

    override fun scrollToHome(){
        index = 0
        scrollByIndex(index)
    }

    private fun scrollByIndex(index: Int) {
        viewPager.setCurrentItem(index)
        bottomNavigationView.getMenu().getItem(index).setChecked(true)
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager.setCurrentItem(0)
                R.id.navigation_search -> viewPager.setCurrentItem(1)
                R.id.navigation_upload -> viewPager.setCurrentItem(2)
                R.id.navigation_favorite -> viewPager.setCurrentItem(3)
                R.id.navigation_profile -> viewPager.setCurrentItem(4)
            }
            true
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                index = position
                bottomNavigationView.getMenu().getItem(index).setChecked(true)
            }

            override fun onPageScrollStateChanged(state: Int) { }
        })

        // Home and Upload Fragments are global for communication purpose
        homeFragment = HomeFragment()
        uploadFragment = UploadFragment()
        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(homeFragment)
        adapter.addFragment(SearchFragment())
        adapter.addFragment(uploadFragment)
        adapter.addFragment(FavoriteFragment())
        adapter.addFragment(ProfileFragment())
        viewPager.adapter = adapter
    }

    private fun setColorStatusBar() {
        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,
            R.color.white)) // set status background white
    }

}