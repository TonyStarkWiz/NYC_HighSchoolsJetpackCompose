package com.example.nyc_highschools.compose.schoollist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.nyc_highschools.R
import com.example.nyc_highschools.data.network.SchoolDetail
import com.example.nyc_highschools.ui.viewmodel.SchoolsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NYCSchoolsListScreen(
    modifier: Modifier = Modifier,
    viewModel: SchoolsListViewModel = hiltViewModel(),
    onSchoolClick: (SchoolDetail) -> Unit = {}
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SchoolsListTopBar(scrollBehavior = scrollBehavior)
        }
    ) {
        SchoolsListView(
            viewModel = viewModel,
            onSchoolClick = onSchoolClick,
            modifier = Modifier.padding(it))
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolsListTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.nyc_schools),
                    color = colorResource(id = R.color.teal_700),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        scrollBehavior = scrollBehavior)
}

@Composable
fun SchoolsListView(
    modifier: Modifier = Modifier,
    viewModel: SchoolsListViewModel,
    onSchoolClick: (SchoolDetail) -> Unit = {}
) {

    val schools: LazyPagingItems<SchoolDetail> by rememberUpdatedState(newValue = viewModel.schools.collectAsLazyPagingItems())

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        items(
            count = schools.itemCount,
            key = {
                index ->
                    val school = schools[index]
                    school?.dbn ?: ""
            }
        ) {
            index ->
                val school = schools[index] ?: return@items

                SchoolListItem(school = school, onClick = {onSchoolClick(school)})


            Divider()
        }

        schools.apply {
            when {
                loadState.refresh is androidx.paging.LoadState.Loading -> {
                    item { LoadItem() }
                }


                loadState.append is androidx.paging.LoadState.Loading -> {
                    item { LoadItem() }
                }
            }
        }
    }
}

@Composable
fun LoadItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .testTag("ProgressbarItem")
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.CenterVertically)
    )
}
