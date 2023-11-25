package com.anderson.nimble.repository

import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.remote.NimbleService
import com.anderson.nimble.domain.model.loginlogout.LoginWithEmailItem
import com.anderson.nimble.domain.model.loginlogout.toLoginWithEmailItem
import com.anderson.nimble.domain.model.survey.SurveyResponseItem
import com.anderson.nimble.domain.model.survey.toSurveyResponseItem
import javax.inject.Inject

class NimbleRepository @Inject constructor(
    private val nimbleService: NimbleService
) {
    companion object{
        const val exception = "Falha ao obter os dados"
    }
    suspend fun loginWithEmail(loginWithEmailRequest: LoginWithEmailRequest) : LoginWithEmailItem {
        return nimbleService.loginWithEmail(loginWithEmailRequest)?.toLoginWithEmailItem() ?: throw Exception(exception)
    }

    suspend fun getSurvey() : SurveyResponseItem {
        return nimbleService.getSurvey()?.toSurveyResponseItem() ?: throw Exception(exception)
    }

//    suspend fun registration(
//        requestBody: Registration
//    ) = nimbleApi.registration(
//        requestBody
//    )
//
//    suspend fun loginWithEmail(
//        requestBody: LoginWithEmailRequest
//    ) = nimbleApi.loginWithEmail(
//        requestBody
//    )
//
//    suspend fun refreshToken(
//        requestBody: RefreshTokenRequest
//    ) = nimbleApi.refreshToken(
//        requestBody
//    )
//
//    suspend fun logout(
//        requestBody: LogoutRequest
//    ) = nimbleApi.logout(
//        requestBody
//    )
//
//    suspend fun forgotPassword(
//        requestBody: ForgotPasswordRequest
//    ) = nimbleApi.forgotPassword(
//        requestBody
//    )
//
//    suspend fun getSurvey() = nimbleApi.getSurvey()
//
//    suspend fun getSurveyDetails(id: String) = nimbleApi.getSurveyDetails(id)
}