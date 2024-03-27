package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
//fun HomeToolbarPreview() {
//    CinemaDummyAppTheme {
//        HomeToolbar(ToolbarState.Home())
//    }
//}

@Composable
fun HomeToolbar(
    state: ToolbarState.Home,
    onCartClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
        )
        Text(
            text = state.title,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Cart(
            tint = Color.White,
            mainViewModel = state.mainViewModel,
            onCartClicked = { onCartClicked() })
    }
}