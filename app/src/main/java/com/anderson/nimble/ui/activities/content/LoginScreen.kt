package com.anderson.nimble.ui.activities.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anderson.nimble.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun LoginScreen() {
    val navController = rememberNavController()
    val nimbleViewModel = viewModel<NimbleViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.LoadScreen.route
    ) {
        composable(Screen.LoginScreen.route) {
            SignIn(nimbleViewModel, navController)
        }

        composable(Screen.LoadScreen.route) {
            LoadScreen()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(nimbleViewModel: NimbleViewModel,
           navController: NavController,
    modifier: Modifier = Modifier) {

    var emailText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = Color(0xff15151a))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginbackground),
                contentDescription = "Background",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Overlay()

        TextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 302.dp,
                    bottom = 454.dp
                )
                .clip(shape = RoundedCornerShape(12.dp))
                .height(IntrinsicSize.Min),
            value = emailText,
            onValueChange = { newEmail -> emailText = newEmail },
            label = {
                Text(
                    text = "Email"
                )
            }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 402.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .height(IntrinsicSize.Min),
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            label = { Text("Password") },
        )

//        EditTextEmail()
//        EditTextPassword()
        ButtonLogin(navController, nimbleViewModel, emailText, password)
        LogoWhite()

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
fun EditTextEmail(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 302.dp,
                bottom = 454.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.White.copy(alpha = 0.18f))
        )
        TextEmail()
    }
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
                    y = 2.dp
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun EditTextPassword(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 362.dp,
                bottom = 395.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.White.copy(alpha = 0.18f))
        )
        TextForgot()
        TextPassword()
    }
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
                    y = 0.800048828125.dp
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
                    y = 2.dp
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun ButtonLogin(navController: NavController, viewModel: NimbleViewModel, email: String, password: String, modifier: Modifier = Modifier) {
    val successfullLoginState = viewModel.successfulLogin.observeAsState()
    var showLoginMessage by remember(viewModel.successfulLogin) { mutableStateOf(false) }

    LaunchedEffect(key1 = successfullLoginState.value) {
        if (successfullLoginState.value == true) {
            navController.navigate(Screen.LoadScreen.route)
        } else {
            showLoginMessage = true
            delay(2000)
            showLoginMessage = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 454.dp,
                bottom = 302.dp
            )
    ) {
        Tab(
            selected = false,
            onClick = {
                viewModel.loginWithEmail(email, password)
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
                        .requiredWidth(width = 335.dp)
                        .requiredHeight(height = 50.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (showLoginMessage) {
            Text(
                text =  "Login error",
                color = Color.Red,
                textAlign = TextAlign.Center,
                lineHeight = 1.29.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    letterSpacing = (-0.41).sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
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