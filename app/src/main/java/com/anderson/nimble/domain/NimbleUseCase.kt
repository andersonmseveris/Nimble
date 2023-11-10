package com.anderson.nimble.domain

import com.anderson.nimble.repository.NimbleRepository
import javax.inject.Inject

class NimbleUseCase @Inject constructor(private val nimbleRepository: NimbleRepository) {
    suspend fun loginWithEmail() {
        return try {
            nimbleRepository.loginWithEmail()
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            throw e
        }
    }
}