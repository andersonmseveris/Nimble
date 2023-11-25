package com.anderson.nimble.ui.activities.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.anderson.nimble.R
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anderson.nimble.ui.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@Composable
fun LoginScreen() {
    val navController = rememberNavController()
    val nimbleViewModel = viewModel<NimbleViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.LoginScreen.route) {
            SignIn(nimbleViewModel, navController)
        }

        composable(Screen.LoadScreen.route) {
            LoadScreen()
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(nimbleViewModel)
        }
    }
}

@Composable
fun SignIn(
    nimbleViewModel: NimbleViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var emailText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        LogoWhite()
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            editTextEmail().let { emailText = it }
            Spacer(modifier = Modifier.padding(8.dp))
            editTextPassword().let { password = it }
            Spacer(modifier = Modifier.padding(4.dp))
            ButtonLogin(navController, nimbleViewModel, emailText, password)
        }


//        Overlay()
    }
}

@Composable
fun Overlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(
                brush = Brush.linearGradient(
                    0f to Color.Black.copy(alpha = 0.2f),
                    1f to Color.Black,
                    start = Offset(0f, 0f),
                    end = Offset(0f, 812f)
                )
            )
    )
}

@Composable
fun editTextEmail(): String {
    var email by remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(56.dp)
            .padding(
                start = 24.dp,
                end = 24.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(
                    color = Color.White.copy(alpha = 0.2f)
                )
        )
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.Transparent),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 17.sp,
                letterSpacing = (-0.41).sp
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
            }
        )
        TextEmail()
    }
    return email.text
}

@Composable
fun TextEmail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Email",
            color = Color.White.copy(alpha = 0.3f),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 17.sp,
                letterSpacing = (-0.41).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(
                    x = 12.dp,
                    y = (-2).dp
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun editTextPassword(): String {
    var password by remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(56.dp)
            .padding(
                start = 24.dp,
                end = 24.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(
                    color = Color.White.copy(alpha = 0.2f)
                )
        )
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.Transparent),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 17.sp,
                letterSpacing = (-0.41).sp
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
            }
        )
        TextPassword()
        TextForgot()
    }

    return password.text
}

@Composable
fun TextForgot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 239.8000030517578.dp)
    ) {
        Text(
            text = "Forgot?",
            color = Color.White.copy(alpha = 0.5f),
            textAlign = TextAlign.End,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.24).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .offset(
                    x = (-12.199996948242188).dp,
                    y = (-2).dp
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun TextPassword(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Password",
            color = Color.White.copy(alpha = 0.3f),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 17.sp,
                letterSpacing = (-0.41).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(
                    x = 12.dp,
                    y = (-2).dp
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun ButtonLogin(
    navController: NavController,
    viewModel: NimbleViewModel,
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {
    val successfulLoginState by viewModel.successfulLogin.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(
                start = 7.dp,
                end = 7.dp
            )
    ) {
        Tab(
            selected = false,
            onClick = {
                scope.launch {
                    viewModel.loginWithEmail(email, password)
                }.invokeOnCompletion {
                    if (successfulLoginState) {
                        navController.navigate(Screen.HomeScreen.route)
                    } else {
                        navController.navigate(Screen.LoadScreen.route)
                    }
                }
            },
            text = {
                Text(
                    text = "Log in",
                    color = Color(0xff15151a),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 17.sp,
                        letterSpacing = (-0.41).sp
                    ),
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(1f)
                        .requiredHeight(height = 56.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
        )
    }
}

@Composable
fun LogoWhite(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 104.dp,
                end = 103.16099548339844.dp,
                top = 153.dp,
                bottom = 619.dp
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.logonimble),
            contentDescription = "Nimble Logo",
            modifier = Modifier
                .fillMaxSize()
        )

    }
}