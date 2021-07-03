package com.example.weber.githubusersdemo.network

import com.example.weber.githubusersdemo.data.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") page: Int
    ): Response<List<Users>>

    @GET("/users/{id}")
    suspend fun getUserInfo(@Path("id") id: String): Response<Users>

}