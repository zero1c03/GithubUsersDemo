package com.example.weber.githubusersdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.weber.githubusersdemo.data.Users
import com.example.weber.githubusersdemo.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    private var currentUsersResult: Flow<PagingData<Users>>? = null

    fun gerUsers(): Flow<PagingData<Users>> {
        val result: Flow<PagingData<Users>> = repository.getUser().cachedIn(viewModelScope)
        currentUsersResult = result
        return result
    }
}