package com.ithirteeng.superfitproject.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ithirteeng.superfitproject.R

val Montserrat = FontFamily(
    Font(R.font.motserrat_400_regular, FontWeight(400)),
    Font(R.font.montserrat_700_bold, FontWeight(700)),
    Font(R.font.montserrat_300_light, FontWeight(300))
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(700),
        fontSize = 64.sp
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(300),
        fontSize = 64.sp
    ),
    h4 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(700),
        fontSize = 36.sp
    ),
    h5 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(700),
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(400),
        fontSize = 18.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(700),
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight(400),
        fontSize = 12.sp
    )


)