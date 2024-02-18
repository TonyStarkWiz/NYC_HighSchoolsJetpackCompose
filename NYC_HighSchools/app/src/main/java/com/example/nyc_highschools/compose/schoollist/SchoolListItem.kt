package com.example.nyc_highschools.compose.schoollist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nyc_highschools.R
import com.example.nyc_highschools.data.network.SchoolDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolListItem(
    school: SchoolDetail,
    onClick:()->Unit = {}
) {

    Card(
        onClick = onClick,
        modifier = Modifier.padding(10.dp)
    ) {

        Text(
            text = school.schoolName,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.Start)
        )

        RowItem(resourceId =R.drawable.ic_user_24 , text = "${school.totalStudents} Students")
        RowItem(resourceId = R.drawable.ic_user_24, text = school.phoneNumber)
        RowItem(resourceId = R.drawable.ic_user_24, text = school.getAddress())

        school.startTime?.let {
            RowItem(resourceId = R.drawable.ic_user_24, text = "${school.startTime} - ${school.endTime}")
        }
    }
}

@Composable
fun RowItem(
    resourceId : Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),

        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "",
            colorFilter = ColorFilter.tint(colorResource(id = R.color.teal_700)))

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            style = MaterialTheme.typography.titleMedium)

    }
}
