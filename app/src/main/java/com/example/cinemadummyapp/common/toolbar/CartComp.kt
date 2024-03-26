package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun CartPreview() {
    CinemaDummyAppTheme {
        Cart(Color.White, listOf(), onCartClicked = {})
    }
}

@Composable
fun Cart(
    tint: Color = Color.White,
    cart: List<Ticket>,
    onCartClicked: () -> Unit
) {
    Box(modifier = Modifier.clickable { onCartClicked() }) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clickable { },
            imageVector = Icons.Filled.ShoppingCart,
            tint = tint,
            contentDescription = null,
        )
        if (cart.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(color = Color.Red, shape = CircleShape),
                text = "  ${cart.count()}  ",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}