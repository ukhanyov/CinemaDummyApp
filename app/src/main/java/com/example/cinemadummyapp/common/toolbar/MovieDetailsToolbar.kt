package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.R

@Composable
fun MovieDetailsToolbar(
    state: ToolbarState.MovieDetails,
    onTabSelected: (Int) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        }
        TabRow(
            modifier = Modifier,
            selectedTabIndex = state.selectedTabIndex,
            containerColor = Color.Black,
            divider = {}
        ) {
            state.allTabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(Color.Black),
                    text = {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    },
                    selected = state.selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                )
            }
        }
    }

}