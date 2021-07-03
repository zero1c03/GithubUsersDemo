package com.example.weber.githubusersdemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.weber.githubusersdemo.data.UsersRepository.Companion.NETWORK_PAGE_SIZE
import com.example.weber.githubusersdemo.network.GithubService

private const val STARTING_PAGE_INDEX = 0

class UsersPagingSource(private val service: GithubService) : PagingSource<Int, Users>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getUsers(page, params.loadSize)
            val users = response.body() as List<Users>
            LoadResult.Page(
                data = users,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - NETWORK_PAGE_SIZE,
                nextKey = page + NETWORK_PAGE_SIZE
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Users>): Int? {
        return state.anchorPosition
    }

}