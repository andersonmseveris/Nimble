package com.anderson.nimble.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anderson.nimble.ui.activities.content.LoginScreen
import com.anderson.nimble.ui.theme.NimbleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginActivityContent()
        }
    }
    @Composable
    fun LoginActivityContent() {
        NimbleTheme {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LoginScreen()
            }
        }
    }
//    private val binding by lazy {
//        ActivityLoginBinding.inflate(layoutInflater)
//    }
//
//    private lateinit var nimbleViewModel: NimbleViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        nimbleViewModel = ViewModelProvider(this)[NimbleViewModel::class.java]
//
//        launch {
////            nimbleViewModel.loginWithEmail()
////            nimbleViewModel.refreshToken()
////            nimbleViewModel.logout()
////            nimbleViewModel.forgotPassword()
////            nimbleViewModel.getSurvey()
////            nimbleViewModel.getSurveyDetails("d5de6a8f8f5f1cfe51bc")
////            nimbleViewModel.registration()
//        }
//        setContentView(binding.root)
//    }
}