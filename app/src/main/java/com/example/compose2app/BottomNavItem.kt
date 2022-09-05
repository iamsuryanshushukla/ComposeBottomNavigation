package com.example.compose2app

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    var label:String,
    var icon:ImageVector,
    var route:String
)
