package com.anderson.nimble.repository

import com.anderson.nimble.data.model.forgotpassword.ForgotPasswordRequest
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.model.loginlogout.LogoutRequest
import com.anderson.nimble.data.model.token.RefreshTokenRequest
import com.anderson.nimble.data.model.registration.Registration
import com.anderson.nimble.data.remote.NimbleServiceApi
import javax.inject.Inject

class NimbleRepository @Inject constructor(
//    private val nimbleService: NimbleService
    private val nimbleServiceApi: NimbleServiceApi
) {
//    suspend fun loginWithEmail() {
//        return nimbleService.loginWithEmail()
//    }

    suspend fun registration(
        requestBody: Registration
    ) = nimbleServiceApi.registration(
        requestBody
    )

    suspend fun loginWithEmail(
        requestBody: LoginWithEmailRequest
    ) = nimbleServiceApi.loginWithEmail(
        requestBody
    )

    suspend fun refreshToken(
        requestBody: RefreshTokenRequest
    ) = nimbleServiceApi.refreshToken(
        requestBody
    )

    suspend fun logout(
        requestBody: LogoutRequest
    ) = nimbleServiceApi.logout(
        requestBody
    )

    suspend fun forgotPassword(
        requestBody: ForgotPasswordRequest
    ) = nimbleServiceApi.forgotPassword(
        requestBody
    )

    suspend fun getSurvey() = nimbleServiceApi.getSurvey()

    suspend fun getSurveyDetails(id: String) = nimbleServiceApi.getSurveyDetails(id)
}