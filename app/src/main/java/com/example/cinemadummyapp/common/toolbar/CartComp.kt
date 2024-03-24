package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun CartPreview() {
    CinemaDummyAppTheme {
        Cart()
    }
}

@Composable
fun Cart(tint: Color = Color.White) {
    Icon(
        modifier = Modifier
            .padding(8.dp)
            .size(48.dp)
            .clickable { },
        imageVector = Icons.Filled.ShoppingCart,
        tint = tint,
        contentDescription = null,
    )
}