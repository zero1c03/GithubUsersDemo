package com.example.weber.githubusersdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weber.githubusersdemo.databinding.FragmentUserInfoBinding
import com.example.weber.githubusersdemo.viewmodels.UserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private val args: UserInfoFragmentArgs by navArgs()
    private val userInfoViewModel: UserInfoViewModel by viewModels()
    private var getUserInfoJob: Job? = null
    private lateinit var binding: FragmentUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        getUserInfo(args.id)
        return binding.root
    }

    private fun getUserInfo(id: String) {
        getUserInfoJob?.cancel()
        getUserInfoJob = lifecycleScope.launch {
            userInfoViewModel.getUserInfo(id).collectLatest {
                binding.userInfo = it
                binding.executePendingBindings()
            }
        }
    }
}