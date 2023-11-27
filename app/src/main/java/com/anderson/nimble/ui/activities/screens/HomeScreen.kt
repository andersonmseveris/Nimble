package com.anderson.nimble.ui.activities.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anderson.nimble.domain.model.survey.SurveyItem
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(nimbleViewModel: NimbleViewModel) {
    val surveyList by nimbleViewModel.surveyList.collectAsState()
    var selectedSurvey by remember { mutableStateOf<SurveyItem?>(null) }

    val scope = rememberCoroutineScope()
    scope.launch {
        nimbleViewModel.getSurvey()
    }

    HomeContent(
        surveys = surveyList,
        onSurveyClick = { selectedSurvey = it }
    )
    var showDialog by remember { mutableStateOf(false) }

    if (selectedSurvey != null) {
//        SurveyDetails(survey = selectedSurvey!!,
//            viewModel = viewModel,
//            onCloseClick = { selectedSurvey = null })
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    surveys: List<SurveyItem>,
    onSurveyClick: (SurveyItem) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()

        HorizontalPager(
            pageCount = surveys.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        { page ->
            PageContent(survey = surveys[page])
            HorizontalPagerIndicator(
                pageCount = surveys.size,
                currentPage = pagerState.currentPage
            )
        }
    }
}

@Composable
fun PageContent(survey: SurveyItem, modifier: Modifier = Modifier) {
//    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
//    val gradient = Brush.verticalGradient(
//        colors = listOf(Color.Transparent, Color.Black),
//        startY = sizeImage.height.toFloat()/3,
//        endY = sizeImage.height.toFloat()
//    )

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.7f }
                .drawWithContent {
                                 val colors = listOf(
                                     Color.Transparent,
                                     Color.Transparent
                                 )
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors)
                    )
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(survey.attributes?.cover_image_url)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
//        Box(modifier = Modifier.matchParentSize().background(gradient))
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    LazyRow(
        Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
    ) {

        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) Color.White else Color.Gray
            item(key = "item$iteration") {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(color, CircleShape)
                        .size(8.dp)
                )
            }
        }
    }
}