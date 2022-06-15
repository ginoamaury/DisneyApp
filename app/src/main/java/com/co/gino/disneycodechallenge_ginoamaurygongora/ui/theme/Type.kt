package com.co.gino.disneycodechallenge_ginoamaurygongora.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.co.gino.disneycodechallenge_ginoamaurygongora.R

val avenirFont = FontFamily(
    Font(R.font.avenir_book),
    Font(R.font.avenir_bold,weight = FontWeight.Bold)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = avenirFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = avenirFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = avenirFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = avenirFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)