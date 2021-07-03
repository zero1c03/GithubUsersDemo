package com.example.weber.githubusersdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weber.githubusersdemo.MainViewPagerFragmentDirections
import com.example.weber.githubusersdemo.data.Users
import com.example.weber.githubusersdemo.databinding.ListItemUsersBinding

class UsersAdapter : PagingDataAdapter<Users, UsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ListItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }


    class UserViewHolder(
        private val binding: ListItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.listUser?.let { user ->
                    navigateToUserInfo(user, view)
                }
            }
        }

        private fun navigateToUserInfo(
            user: Users,
            view: View
        ) {
            val direction =
                MainViewPagerFragmentDirections.actionNavigationUsersToNavigationUserInfo(user.login)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Users) {
            binding.apply {
                listUser = item
                executePendingBindings()
            }
        }
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }

    }
}