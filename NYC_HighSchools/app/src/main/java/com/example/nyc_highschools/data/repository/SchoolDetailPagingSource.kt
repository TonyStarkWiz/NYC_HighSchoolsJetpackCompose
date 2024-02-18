package com.example.nyc_highschools.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nyc_highschools.data.network.NYCApiService
import com.example.nyc_highschools.data.network.SchoolDetail

private const val STARTING_OFFSET = 0
class SchoolDetailPagingSource (private val apiService: NYCApiService) : PagingSource<Int, SchoolDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SchoolDetail> {
        val page = params.key ?: STARTING_OFFSET

        return try {
            val response = apiService.getSchools(limit = params.loadSize, offset = page * 10)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SchoolDetail>): Int? {
        return state.anchorPosition
    }
}
