package com.unbuniworks.camusat.efiber.ui.commonComponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R
import kotlinx.coroutines.launch

@Composable
fun CustomCircularProgressBar(progress: Float, modifier: Modifier = Modifier) {
    var animatedProgress by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(progress) {
        scope.launch {
            animatedProgress = progress
        }
    }

    val color = colorResource(id = R.color.button_color)
    Canvas(
        modifier = modifier,
        onDraw = {
            val outerRadius = size.minDimension / 2f
            val innerRadius = outerRadius - 20.dp.toPx()
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val progressAngle = animatedProgress * 270 // Use 270 degrees instead of 360


            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 270f, // Use 270 degrees for the complete circle
                useCenter = false,
                style = Stroke(20f)
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = progressAngle,
                useCenter = false,
                style = Stroke(20f),
            )
        }
    )
}

