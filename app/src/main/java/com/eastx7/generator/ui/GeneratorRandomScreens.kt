package com.eastx7.generator.ui

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.RectF
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.graphics.shapes.*
import com.eastx7.generator.MainActivity.Companion.DURATION_MILLIS_PER_DIGIT
import com.eastx7.generator.R
import com.eastx7.generator.data.GeneratorSettings
import com.eastx7.generator.theme.*
import com.eastx7.generator.MainActivity.ExhibitionType
import kotlin.math.min

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RandomBody(
    innerPadding: PaddingValues,
    generatorSettings: GeneratorSettings,
    onStartClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp -
            innerPadding.calculateTopPadding() -
            startEndPaddings
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp -
            startEndPaddings * 2

    val primaryColor = MaterialTheme.colorScheme.primary
    val displayHeight = screenHeight * .7f
    val dashboardHeight = screenHeight - displayHeight -
            startEndPaddings * 2
    val sizeImage = screenWidth * 0.2f

    val numDigits = generatorSettings.endNum.toString().length
    val displayInit = generatorSettings.digitDisplay
    val digitViewWidth = screenWidth / numDigits - startEndPaddings * (numDigits - 1)

    Column(
        modifier = Modifier
            .padding(
                top = innerPadding.calculateTopPadding(),
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(numDigits) { displayIndex ->
                val digitRandom = displayInit[displayIndex]
                val digitRandomInt = digitRandom.digitToInt()

                val crossDigitAnimation = remember(digitRandom) {
                    Animatable(0f)
                }

                LaunchedEffect(digitRandom) {
                    val animationSpec: TweenSpec<Float> = tween(
                        durationMillis =
                        (digitRandomInt * DURATION_MILLIS_PER_DIGIT)
                            .coerceAtMost(2000),
                        easing = EaseOutQuart
                    )
                    crossDigitAnimation.animateTo(
                        targetValue = 1f,
                        animationSpec = animationSpec
                    )
                }

                val digitCross = (crossDigitAnimation.value * digitRandomInt).toInt()

                AnimatedContent(
                    targetState = digitCross,
                    transitionSpec = {
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    }
                ) { targetCount ->
                    Box(
                        Modifier
                            .width(digitViewWidth)
                            .height(displayHeight)
                            .padding(2.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = GeneratorShapes.large
                            ),
                    ) {
                        RandomBodyDisplayDigit(
                            digitCross,
                            primaryColor
                        )
                    }
                }
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(dashboardHeight),
        ) {
            RandomBodyDashboard(
                generatorSettings = generatorSettings,
                width = LocalConfiguration.current.screenWidthDp.dp,
                height = dashboardHeight,
                sizeImage = sizeImage,
                primaryColor = primaryColor,
                onStartClick = onStartClick
            )
        }
    }
}

@Composable
fun RandomBodyDisplayDigit(
    digit: Int,
    primaryColor: Color
) {
    val backgroudShape = when (digit) {
        9 -> {
            Star(
                numVerticesPerRadius = 9,
                innerRadius = 0.4f,
                rounding = CornerRounding(0.05f, 0.05f),
                innerRounding = CornerRounding(
                    0.05f,
                    0.05f
                ),
                radius = 0.5f,
                center = PointF(0.5f, 0.5f)
            )
        }
        8 -> {
            Star(
                numVerticesPerRadius = 8,
                innerRadius = 0.25f,
                rounding = CornerRounding(0.05f, 0.05f),
                innerRounding = CornerRounding(
                    0.05f,
                    0.05f
                ),
                radius = 0.6f,
                center = PointF(0.5f, 0.5f)
            )
        }
        7 -> {
            RoundedPolygon(
                listOf(
                    PointF(0.5f, 0f),
                    PointF(0.910f, 0.206f),
                    PointF(1f, 0.64f),
                    PointF(0.740f, 1f),
                    PointF(0.288f, 1f),
                    PointF(0f, 0.642f),
                    PointF(0.112f, 0.198f),
                ),
                rounding = CornerRounding(0.05f, 0.05f),
                center = PointF(0f, 0f)
            )
        }
        6 -> {
            Star(
                numVerticesPerRadius = 6,
                innerRadius = 0.25f,
                rounding = CornerRounding(0.05f, 0.05f),
                innerRounding = CornerRounding(
                    0.05f,
                    0.05f
                ),
                radius = 0.6f,
                center = PointF(0.5f, 0.5f)
            )
        }
        5 -> {
            Star(
                numVerticesPerRadius = 5,
                innerRadius = 0.25f,
                rounding = CornerRounding(0.05f, 0.05f),
                innerRounding = CornerRounding(
                    0.05f,
                    0.05f
                ),
                radius = 0.6f,
                center = PointF(0.5f, 0.5f)
            )
        }
        4 -> {
            RoundedPolygon(
                listOf(
                    PointF(0f, 0f),
                    PointF(1f, 0f),
                    PointF(1f, 1f),
                    PointF(0f, 1f),
                ),
                rounding = CornerRounding(0.07f, 0.07f),
                center = PointF(0f, 0f)
            )
        }
        3 -> {
            RoundedPolygon(
                listOf(
                    PointF(0f, 1f),
                    PointF(0.5f, 0f),
                    PointF(1f, 1f),
                ),
                rounding = CornerRounding(0.05f, 0.05f),
                center = PointF(0f, 0f)
            )
        }
        2 -> {
            RoundedPolygon(
                listOf(
                    PointF(0.5f, 0f),
                    PointF(1f, 0.2f),
                    PointF(1f, 1f),
                    PointF(0f, 1f),
                    PointF(0f, 0.2f),
                ),
                rounding = CornerRounding(0.05f, 0.05f),
                center = PointF(0f, 0f)
            )
        }
        1 -> {
            RoundedPolygon(
                listOf(
                    PointF(1f, 0f),
                    PointF(1f, 1f),
                    PointF(0f, 1f),
                    PointF(0f, 0.2f),
                ),
                rounding = CornerRounding(0.05f, 0.05f),
                center = PointF(0f, 0f)
            )
        }
        else -> {
            RoundedPolygon(
                listOf(
                    PointF(0f, 0f),
                    PointF(1f, 0f),
                    PointF(1f, 1f),
                    PointF(0f, 1f),
                ),
                rounding = CornerRounding(0.5f, 0.5f),
                center = PointF(0f, 0f)
            )
        }
    }
    val sizedPolygonCache = remember(backgroudShape) {
        mutableMapOf<Size, RoundedPolygon>()
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(startEndPaddings)
            .drawBehind {
                val sizedPolygon = sizedPolygonCache.getOrPut(size) {
                    val matrix = calculateMatrix(
                        RectF(0f, 0f, 1f, 1f),
                        size.width,
                        size.height
                    )
                    RoundedPolygon(backgroudShape).apply {
                        transform(matrix)
                    }
                }
                drawPath(
                    sizedPolygon
                        .toPath()
                        .asComposePath(),
                    primaryColor
                )

            },
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = digit.toString(),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            style = GeneratorTypography.headlineLarge,
            color = MaterialTheme.colorScheme.background
        )
    }
}

fun calculateMatrix(bounds: RectF, width: Float, height: Float): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}

@Composable
fun RandomBodyDashboard(
    generatorSettings: GeneratorSettings,
    width: Dp,
    height: Dp,
    sizeImage: Dp,
    primaryColor: Color,
    onStartClick: () -> Unit
) {
    val widthPx = with(LocalDensity.current) { (width).toPx() }
    val heightPx = with(LocalDensity.current) { (height).toPx() }
    val textStyle = GeneratorTypography.headlineMedium
    val textColor = MaterialTheme.colorScheme.background
    val backgroudShape = RoundedPolygon(
        listOf(
            PointF(0f, 0.5f),//5
            PointF(0f, 0.192f),//4
            PointF(0.313f, 0.188f),//3
            PointF(0.333f, 0.133f),//2
            PointF(0.375f, 0.057f),//1
            PointF(0.443f, 0.015f),//0
            PointF(0.5f, 0f),
            PointF(0.557f, 0.015f),//0
            PointF(0.625f, 0.057f),//1
            PointF(0.667f, 0.133f),//2
            PointF(0.687f, 0.188f),//3
            PointF(1f, 0.192f),//4
            PointF(1f, 0.5f)//5
        ),
        rounding = CornerRounding(0.18f, 0.18f),
        center = PointF(0f, 0f)
    )
    val sizedPolygonCache = remember(backgroudShape) {
        mutableMapOf<Size, RoundedPolygon>()
    }
    Box(
        Modifier
            .width(width)
            .height(height)
            .drawBehind {
                val sizedPolygon = sizedPolygonCache.getOrPut(Size(widthPx, heightPx)) {
                    val matrix = calculateMatrix(
                        bounds = RectF(0f, 0f, 1f, 0.5f),
                        width = widthPx,
                        height = heightPx
                    )
                    RoundedPolygon(backgroudShape).apply {
                        transform(matrix)
                    }
                }
                drawPath(
                    sizedPolygon
                        .toPath()
                        .asComposePath(),
                    primaryColor
                )
            }
    ) {
        Image(
            modifier = Modifier
                .width(sizeImage)
                .aspectRatio(1f)
                .padding(7.dp)
                .clickable(onClick = onStartClick)
                .align(Alignment.TopCenter)
                .border(
                    width = 2.dp,
                    color = Color.Red,
                    shape = CircleShape
                )
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(
                id = if (generatorSettings.exhibitionType == ExhibitionType.Dogs) {
                    R.drawable.dog
                } else {
                    R.drawable.cat
                }
            ),
            contentDescription = "start"
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = height * 0.6f)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center
        ) {
            val textWidth = width / 4
            Text(
                text = generatorSettings.startNum.toString(),
                modifier = Modifier
                    .width(textWidth)
                    .wrapContentHeight(),
                style = textStyle,
                color = textColor
            )
            Text(
                text = "-",
                modifier = Modifier
                    .width(textWidth)
                    .wrapContentHeight(),
                style = textStyle.copy(textAlign = TextAlign.Center),
                color = textColor
            )
            Text(
                text = generatorSettings.endNum.toString(),
                modifier = Modifier
                    .width(textWidth)
                    .wrapContentHeight(),
                style = textStyle.copy(textAlign = TextAlign.Right),
                color = textColor
            )
        }
    }
}