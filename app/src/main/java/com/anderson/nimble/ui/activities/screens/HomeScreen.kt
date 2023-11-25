package com.anderson.nimble.ui.activities.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
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
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Título do Alerta") },
            text = { Text("Mensagem do Alerta") },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(selectedSurvey!!.id)
                }
            }
        )
    }
}

@Composable
fun HomeContent(
    surveys: List<SurveyItem>,
    onSurveyClick: (SurveyItem) -> Unit
) {
    var gridCells by remember { mutableStateOf(GridCells.Fixed(2)) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = gridCells,
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize()
        )  {
            items(surveys) { survey: SurveyItem ->
                SurveyLayout(survey = survey, onSurveyClick = { onSurveyClick(survey) })
            }
        }
    }
}

@Composable
fun SurveyLayout(survey: SurveyItem, onSurveyClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = rememberImagePainter(survey.attributes?.cover_image_url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onSurveyClick() },
                    contentScale = ContentScale.Crop,
                )
            }
        }

//        Image(
//            painter = rememberImagePainter(data = survey.attributes?.cover_image_url),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//                .clickable { onSurveyClick() },
//            contentScale = ContentScale.Crop,
//        )

//        Column(
//            modifier = Modifier
//                .background(color = Color(0xFF15151A))
//        ) {
//
//            Scaffold(
//                modifier = Modifier.padding(all = 20.dp),
//                topBar = {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Column(verticalArrangement = Arrangement.Center) {
//                            Spacer(
//                                modifier = Modifier
//                                    .height(20.dp)
//                                    .clip(shape = RoundedCornerShape(10.dp))
//                                    .fillMaxWidth(fraction = 0.4f)
//                            )
//                            Spacer(modifier = Modifier.padding(8.dp))
//                            Spacer(
//                                modifier = Modifier
//                                    .height(20.dp)
//                                    .clip(shape = RoundedCornerShape(10.dp))
//                                    .fillMaxWidth(fraction = 0.3f)
//                            )
//                        }
//                        Spacer(
//                            modifier = Modifier.weight(1f)
//                        )
//                        Box(
//                            modifier = Modifier
//                                .size(40.dp)
//                                .clip(CircleShape)
//                        )
//                    }
//                },
//                bottomBar = {
//                    Column(
//                        modifier =
//                        Modifier
//                            .fillMaxWidth()
//                    ) {
//
//                        Spacer(
//                            modifier = Modifier
//                                .height(20.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .fillMaxWidth(fraction = 0.1f)
//                        )
//                        Spacer(modifier = Modifier.padding(8.dp))
//                        Spacer(
//                            modifier = Modifier
//                                .height(20.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .fillMaxWidth(fraction = 0.7f)
//                        )
//                        Spacer(modifier = Modifier.padding(5.dp))
//                        Spacer(
//                            modifier = Modifier
//                                .height(20.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .fillMaxWidth(fraction = 0.4f)
//                        )
//                        Spacer(modifier = Modifier.padding(8.dp))
//                        Spacer(
//                            modifier = Modifier
//                                .height(20.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .fillMaxWidth(fraction = 0.9f)
//                        )
//                        Spacer(modifier = Modifier.padding(5.dp))
//                        Spacer(
//                            modifier = Modifier
//                                .height(20.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .fillMaxWidth(fraction = 0.6f)
//                        )
//                    }
//                }
//            ) {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = Color(0xFF15151A)
//                ) {}
//            }
//        }
    }
}