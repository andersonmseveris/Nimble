package com.anderson.nimble.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.anderson.nimble.databinding.ActivityLoginBinding
import com.anderson.nimble.ui.viewmodel.NimbleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), CoroutineScope by MainScope(){

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var nimbleViewModel: NimbleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nimbleViewModel = ViewModelProvider(this)[NimbleViewModel::class.java]

        launch {
            nimbleViewModel.loginWithEmail()
            nimbleViewModel.refreshToken()
            nimbleViewModel.logout()
            nimbleViewModel.forgotPassword()
//            nimbleViewModel.registration()
        }
        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show()

        setContentView(binding.root)
    }
}