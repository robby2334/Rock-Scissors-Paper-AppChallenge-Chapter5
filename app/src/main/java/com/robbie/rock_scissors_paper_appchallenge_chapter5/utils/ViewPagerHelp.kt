package com.robbie.rock_scissors_paper_appchallenge_chapter5.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerHelp (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle){

    private val fragmentList: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment) {
        if (fragment.isAdded && fragmentList.contains(fragment)) {
            return
        }
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}