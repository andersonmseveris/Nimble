package com.anderson.nimble.ui.activities.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.anderson.nimble.R
import com.anderson.nimble.domain.model.survey.SurveyItem
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(nimbleViewModel: NimbleViewModel) {
    val surveyList by nimbleViewModel.surveyList.collectAsState()
    var selectedSurvey by remember { mutableStateOf<SurveyItem?>(null) }

    val scope = rememberCoroutineScope()
    scope.launch {
        nimbleViewModel.getSurvey()
    }

//    Home()
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    surveys: List<SurveyItem>,
    onSurveyClick: (SurveyItem) -> Unit
) {
    val pagerState = rememberPagerState()
    Column {
        HorizontalPager(
            pageCount = surveys.size,
            state = pagerState
        )
        { page ->
            SurveyImage(
                survey = surveys[page],
                onSurveyClick = { onSurveyClick(surveys[page]) }
            )
        }

        HorizontalPagerIndicator(
            pageCount = surveys.size,
            currentPage = pagerState.currentPage,
            targetPage = pagerState.targetPage,
            currentPageOffsetFraction = pagerState.currentPageOffsetFraction
        )
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Color.DarkGray,
    unselectedIndicatorSize: Dp = 8.dp,
    selectedIndicatorSize: Dp = 10.dp,
    indicatorCornerRadius: Dp = 2.dp,
    indicatorPadding: Dp = 2.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .height(selectedIndicatorSize + indicatorPadding * 2)
    ) {

        // draw an indicator for each page
        repeat(pageCount) { page ->
            // calculate color and size of the indicator
            val (color, size) =
                if (currentPage == page || targetPage == page) {
                    // calculate page offset
                    val pageOffset =
                        ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                    // calculate offset percentage between 0.0 and 1.0
                    val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                    val size =
                        unselectedIndicatorSize + ((selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage)

                    indicatorColor.copy(
                        alpha = offsetPercentage
                    ) to size
                } else {
                    indicatorColor.copy(alpha = 0.1f) to unselectedIndicatorSize
                }

            // draw indicator
            Box(
                modifier = Modifier
                    .padding(
                        // apply horizontal padding, so that each indicator is same width
                        horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2,
                        vertical = size / 4
                    )
                    .clip(RoundedCornerShape(indicatorCornerRadius))
                    .background(color)
                    .width(size)
                    .height(size / 2)
            )
        }
    }
}

@Composable
fun SurveyImage(survey: SurveyItem, onSurveyClick: () -> Unit) {
    val painter: Painter = rememberAsyncImagePainter(
        model = survey.attributes?.cover_image_url
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .paint(
                painter,
                contentScale = ContentScale.FillBounds
            )
    ) {

    }
}


@Composable
fun HomeContent2(
    surveys: List<SurveyItem>,
    onSurveyClick: (SurveyItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize()
        )  {
            items(surveys) { survey: SurveyItem ->
                SurveyLayout(survey = survey, onSurveyClick = { onSurveyClick(survey) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SurveyLayout(survey: SurveyItem, onSurveyClick: () -> Unit) {

}


@Composable
fun Home(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Background",
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color.Black,
                            1f to Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(0f, 812f)
                        )
                    )
            )
        }
        Text(
            text = "Working from home Check-In",
            color = Color.White,
            lineHeight = 1.21.em,
            style = TextStyle(
                fontSize = 28.sp,
                letterSpacing = (-0.5).sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.Bottom)
        )
        Text(
            text = "We would like to know how you feel about our work from home...",
            color = Color.White.copy(alpha = 0.7f),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 17.sp,
                letterSpacing = (-0.41).sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .offset(
                    x = (-20).dp,
                    y = (-54).dp
                )
                .requiredSize(size = 56.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape)
                    .background(color = Color.White)
            )
            Image(
                painter = painterResource(id = R.drawable.logonimble),
                contentDescription = "Assets / Actions / Next  Accent",
                colorFilter = ColorFilter.tint(Color(0xff15151a)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 14.dp,
                        vertical = 13.dp
                    )
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 20.dp,
                    y = 60.dp
                )
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 63.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Userpic",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 319.dp,
                        end = 20.dp,
                        top = 79.dp,
                        bottom = 52.dp
                    )
            )
            Text(
                text = "Today",
                color = Color.White,
                lineHeight = 1.21.em,
                style = TextStyle(
                    fontSize = 34.sp,
                    letterSpacing = (-1).sp
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = "MONDAY, JUNE 15",
                color = Color.White,
                lineHeight = 1.38.em,
                style = TextStyle(
                    fontSize = 13.sp,
                    letterSpacing = (-0.08).sp
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}