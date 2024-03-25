package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun TheaterSeatsToolbarPreview() {
//    CinemaDummyAppTheme {
//        TheaterSeatsToolbar()
//    }
//}

@Composable
fun TheaterSeatsToolbar(
    state: ToolbarState.TheaterSeats,
    onBackClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clickable { onBackClicked() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.Black,
            contentDescription = null,
        )
        Text(
            modifier = Modifier,
            text = state.title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Cart(tint = Color.Black, count = state.cart.count())
    }
}