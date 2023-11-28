package com.anderson.nimble.ui.activities.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.anderson.nimble.R
import com.anderson.nimble.domain.model.survey.SurveyItem
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
            PageContent(survey = surveys[page], pagerState, surveys.size)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PageContent(
    survey: SurveyItem,
    pagerState: PagerState,
    surveysSize: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.7f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Transparent,
                        Color.Black
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
        Box(
            modifier = Modifier
                .padding(all = 20.dp)
                .background(Color.Transparent),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.Center)
                {
                    Text(
                        text = displayDate(dateString = survey.attributes?.created_at.toString()),
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.neuzeitsltstd)),
                            fontWeight = FontWeight(800),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Text(
                        text = displayDay(dateString = survey.attributes?.active_at.toString()),
                        style = TextStyle(
                            fontSize = 34.sp,
                            lineHeight = 41.sp,
                            fontFamily = FontFamily(Font(R.font.neuzeitsltstd)),
                            fontWeight = FontWeight(800),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.userpic),
                        contentDescription = "User Pic",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    HorizontalPagerIndicator(
                        pageCount = surveysSize,
                        currentPage = pagerState.currentPage
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = survey.attributes?.title.toString(),
                        style = TextStyle(
                            fontSize = 28.sp,
                            lineHeight = 34.sp,
                            fontFamily = FontFamily(Font(R.font.neuzeitsltstd)),
                            fontWeight = FontWeight(800),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = survey.attributes?.description.toString(),
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.neuzeitsltstd)),
                            fontWeight = FontWeight(400),
                            color = Color.LightGray,

                            )
                    )
                }
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
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
        horizontalArrangement = Arrangement.Start
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

@Composable
fun displayDate(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date: Date = dateFormat.parse(dateString) ?: Date()

    val dateFormatter = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
    return dateFormatter.format(date).uppercase(Locale.ROOT)
}

@Composable
fun displayDay(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date: Date = dateFormat.parse(dateString) ?: Date()

    val dateFormatter = SimpleDateFormat("EEEE", Locale.getDefault())
    val currentDate = Calendar.getInstance().time
    val isToday = dateFormat.format(currentDate) == dateFormat.format(date)

    return if (isToday) {
        "Today"
    } else {
        dateFormatter.format(date)
    }
}