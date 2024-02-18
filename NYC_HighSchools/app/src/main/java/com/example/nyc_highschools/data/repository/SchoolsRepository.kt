package com.example.nyc_highschools.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nyc_highschools.data.network.NYCApiService
import com.example.nyc_highschools.data.network.SchoolDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolsRepository @Inject constructor(private val api: NYCApiService) {

    fun getSchools() : Flow<PagingData<SchoolDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                ),
            pagingSourceFactory = { SchoolDetailPagingSource(api) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE  = 10
    }

}
