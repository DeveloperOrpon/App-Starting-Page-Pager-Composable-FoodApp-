package com.example.gettingstartpage

import androidx.compose.ui.graphics.Color
import com.uistack.onboarding.ui.theme.ColorBlue

data class Data(
    val image: Int, val title: String,
    val desc: String,
    val backgroundColor:Color,
    val mainColor:Color = ColorBlue,
)
