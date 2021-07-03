package com.example.weber.githubusersdemo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weber.githubusersdemo.MineFragment
import com.example.weber.githubusersdemo.UsersFragment

const val USERS_PAGE_INDEX = 0
const val MINE_PAGE_INDEX = 1

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        USERS_PAGE_INDEX to { UsersFragment() },
        MINE_PAGE_INDEX to { MineFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}