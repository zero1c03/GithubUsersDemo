package com.example.weber.githubusersdemo.data

import com.example.weber.githubusersdemo.network.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserInfoRepository @Inject constructor(private val service: GithubService) {
    fun getUserInfo(id: String): Flow<Users?> {
        return flow {
            emit(service.getUserInfo(id).body())
        }.flowOn(Dispatchers.IO)
    }
}