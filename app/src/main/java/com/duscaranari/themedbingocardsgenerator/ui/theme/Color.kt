package com.duscaranari.themedbingocardsgenerator.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val SphereDetail = Color(0xB3855F45)
val SphereBrown = Color(0xB3240E04)

// ONLINE BINGO COLOR PALETTE
val OnlineLightPrimary = Color(0xff3543f3)
val OnlineLightOnPrimary = Color(0xffffffff)
val OnlineLightPrimaryContainer = Color(0xffe0e0ff)
val OnlineLightOnPrimaryContainer = Color(0xff000569)
val OnlineDarkPrimary = Color(0xffbec2ff)
val OnlineDarkOnPrimary = Color(0xff000ba6)
val OnlineDarkPrimaryContainer = Color(0xff0e1edd)
val OnlineDarkOnPrimaryContainer = Color(0xffe0e0ff)

// THEMED BINGO COLOR PALETTE
val ThemedLightPrimary = Color(0xff006d39)
val ThemedLightOnPrimary = Color(0xffffffff)
val ThemedLightPrimaryContainer = Color(0xff5eff9c)
val ThemedLightOnPrimaryContainer = Color(0xff00210d)
val ThemedDarkPrimary = Color(0xff7fda99)
val ThemedDarkOnPrimary = Color(0xff00391b)
val ThemedDarkPrimaryContainer = Color(0xff00522a)
val ThemedDarkOnPrimaryContainer = Color(0xff9af6b3)

// CLASSIC BINGO COLOR PALETTE
val ClassicLightPrimary = Color(0xff9c4400)
val ClassicLightOnPrimary = Color(0xffffffff)
val ClassicLightPrimaryContainer = Color(0xffffdbca)
val ClassicLightOnPrimaryContainer = Color(0xff331200)
val ClassicDarkPrimary = Color(0xffffb68f)
val ClassicDarkOnPrimary = Color(0xff542100)
val ClassicDarkPrimaryContainer = Color(0xff773200)
val ClassicDarkOnPrimaryContainer = Color(0xffffdbca)


fun getRandomLightColor() =
    Color(
        blue = Random.nextInt(160, 256),
        red = Random.nextInt(160, 256),
        green = Random.nextInt(160, 256),
        alpha = 255
    )