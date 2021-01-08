package com.alorma.dogarty.ui

import androidx.annotation.AttrRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import com.alorma.dogarty.R
import com.google.android.material.color.MaterialColors

@Composable
fun DogartyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colorPrimary = obtainColor(R.attr.colorPrimary)
    val colorSecondary = obtainColor(R.attr.colorSecondary)

    val colors = if (darkTheme) {
        darkColors(
            primary = Color(colorPrimary),
            secondary = Color(colorSecondary)
        )
    } else {
        lightColors(
            primary = Color(colorPrimary),
            secondary = Color(colorSecondary)
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
private fun obtainColor(@AttrRes colorAttr: Int) =
    MaterialColors.getColor(AmbientContext.current, colorAttr, "")