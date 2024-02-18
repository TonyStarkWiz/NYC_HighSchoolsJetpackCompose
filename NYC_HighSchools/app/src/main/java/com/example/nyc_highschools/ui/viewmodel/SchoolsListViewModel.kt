package com.example.nyc_highschools.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nyc_highschools.data.network.SchoolDetail
import com.example.nyc_highschools.data.repository.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SchoolsListViewModel @Inject constructor(repository: SchoolsRepository)
    :ViewModel() {

    var schools: Flow<PagingData<SchoolDetail>> = repository.getSchools().cachedIn(viewModelScope)
}
