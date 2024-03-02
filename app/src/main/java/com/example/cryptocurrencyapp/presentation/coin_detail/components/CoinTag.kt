package com.example.cryptocurrencyapp.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencyapp.presentation.ui.BoxColor
import com.example.cryptocurrencyapp.presentation.ui.TagBoxColor

@Composable
fun CoinTag(tag: String) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = BoxColor,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
    ){
        Text(
            text = tag,
            color = TagBoxColor,
            textAlign = TextAlign.Center
        )
    }
}