package com.example.weber.githubusersdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weber.githubusersdemo.adapter.MINE_PAGE_INDEX
import com.example.weber.githubusersdemo.adapter.MainPagerAdapter
import com.example.weber.githubusersdemo.adapter.USERS_PAGE_INDEX
import com.example.weber.githubusersdemo.databinding.FragmentMainViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = MainPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        binding.toolbar.title = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.toolbar.title = tab?.text
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            USERS_PAGE_INDEX -> R.drawable.ic_users_selector
            MINE_PAGE_INDEX -> R.drawable.ic_mine_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            USERS_PAGE_INDEX -> {
                getString(R.string.title_user)
            }
            MINE_PAGE_INDEX -> {
                getString(R.string.title_mine)
            }
            else -> null
        }
    }
}