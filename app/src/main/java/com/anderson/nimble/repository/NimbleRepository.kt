package com.anderson.nimble.repository

import com.anderson.nimble.data.remote.NimbleService
import javax.inject.Inject

class NimbleRepository @Inject constructor(private val nimbleService: NimbleService) {
    suspend fun loginWithEmail() {
        return nimbleService.loginWithEmail()
    }
}