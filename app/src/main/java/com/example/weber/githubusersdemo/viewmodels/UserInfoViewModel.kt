package com.example.weber.githubusersdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weber.githubusersdemo.data.UserInfoRepository
import com.example.weber.githubusersdemo.data.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val repository: UserInfoRepository) :
    ViewModel() {

    fun getUserInfo(id: String): Flow<Users?> {
        return repository.getUserInfo(id)
    }

}