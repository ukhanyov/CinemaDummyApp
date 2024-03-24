package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailsToolbar(
    state: ToolbarState.MovieDetails,
    onTabSelected: (Int) -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clickable { onBackClicked() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = null,
        )
        TabRow(
            modifier = Modifier.weight(1f),
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
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clickable { },
            imageVector = Icons.Filled.ShoppingCart,
            tint = Color.White,
            contentDescription = null,
        )
    }

}