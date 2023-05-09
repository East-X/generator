package com.eastx7.generator.theme

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VertFieldSpacer() {
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun HorizontalFieldSpacer() {
    Spacer(modifier = Modifier.width(8.dp))
}
