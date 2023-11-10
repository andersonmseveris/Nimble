package com.anderson.nimble.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.nimble.domain.NimbleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NimbleViewModel
@Inject constructor(
    private val nimbleUseCase: NimbleUseCase
) : ViewModel() {

    fun loginWithEmail() {
        viewModelScope.launch {
            try {
                nimbleUseCase.loginWithEmail()
            } catch (_: Exception) {
                TODO()
            }
        }
    }
}