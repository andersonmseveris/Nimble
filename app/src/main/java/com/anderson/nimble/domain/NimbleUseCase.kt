package com.anderson.nimble.domain

import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.domain.model.loginlogout.LoginWithEmailItem
import com.anderson.nimble.domain.model.survey.SurveyResponseItem
import com.anderson.nimble.repository.NimbleRepository
import javax.inject.Inject

class NimbleUseCase @Inject constructor(private val nimbleRepository: NimbleRepository) {
    suspend fun loginWithEmail(loginWithEmailRequest: LoginWithEmailRequest) : LoginWithEmailItem {
        return try {
            nimbleRepository.loginWithEmail(loginWithEmailRequest)
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            throw e
        }
    }

    suspend fun getSurvey() : SurveyResponseItem {
        return try {
            nimbleRepository.getSurvey()
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            throw e
        }
    }
}