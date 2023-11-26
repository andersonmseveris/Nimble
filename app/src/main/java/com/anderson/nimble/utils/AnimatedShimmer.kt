package com.anderson.nimble.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(translateAnim.value,0f)
    )

    ShimmerGridItem(brush = brush)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(modifier = Modifier
        .background(color = Color(0xFF15151A))) {
        Scaffold(
            modifier = Modifier.padding(all = 20.dp),
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .fillMaxWidth(fraction = 0.4f)
                                .background(brush)
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .fillMaxWidth(fraction = 0.3f)
                                .background(brush)
                        )
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(brush)
                    )
                }
            },
            bottomBar = {
                Column(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                ) {

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.1f)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.7f)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.4f)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.9f)
                            .background(brush)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth(fraction = 0.6f)
                            .background(brush)
                    )
                }
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize(),
                color = Color(0xFF15151A)
            ){}
        }
    }
}