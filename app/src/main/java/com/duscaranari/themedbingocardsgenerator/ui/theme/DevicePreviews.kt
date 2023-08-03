package com.duscaranari.themedbingocardsgenerator.ui.theme

import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "phone_portrait", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480", showBackground = true)
@Preview(name = "tablet_portrait", device = "spec:shape=Normal,width=800,height=1280,unit=dp,dpi=480", showBackground = true)
annotation class PortraitPreviews

@Preview(name = "phone_landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480", showBackground = true)
@Preview(name = "tablet_landscape", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480", showBackground = true)
annotation class LandscapePreviews

@Preview(name = "pixel4", device = "id:pixel_4", showBackground = true)
annotation class Pixel4