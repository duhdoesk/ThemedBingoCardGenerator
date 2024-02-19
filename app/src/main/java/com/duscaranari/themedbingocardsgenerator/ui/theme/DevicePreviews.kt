package com.duscaranari.themedbingocardsgenerator.ui.theme

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "A - Smaller Phone", device = "spec: width= 360dp, height= 640dp, dpi= 480, orientation= portrait", showSystemUi = true)
@Preview(name = "B - Larger Phone", device = Devices.PHONE, showSystemUi = true)
@Preview(name = "C - Unfolded Foldable", device = Devices.FOLDABLE, showSystemUi = true)
@Preview(name = "D - Tablet", device = "spec: width= 1280dp, height= 800dp, orientation= portrait, dpi= 420", showSystemUi = true)
annotation class PortraitPreviews

@Preview(name = "A - Smaller Phone", device = "spec: width= 640dp, height= 360dp, dpi= 480, orientation= landscape", showSystemUi = true)
@Preview(name = "B - Larger Phone", device = "spec: width= 891dp, height= 411dp, dpi=420, orientation= landscape", showSystemUi = true)
@Preview(name = "C - Unfolded Foldable", device = "spec: width= 673dp, height= 841dp, dpi=420, orientation= landscape", showSystemUi = true)
@Preview(name = "D - Tablet", device = Devices.TABLET, showSystemUi = true)
@Preview(name = "E - Desktop", device = Devices.DESKTOP, showSystemUi = true)
annotation class LandscapePreviews