package com.xparkle.githubuserapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionAdapt(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String? = null
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowsFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowsFragment.ARG_POSITION, position + 1)
            putString(FollowsFragment.ARG_USERNAME, username)
        }
        return fragment
    }
}