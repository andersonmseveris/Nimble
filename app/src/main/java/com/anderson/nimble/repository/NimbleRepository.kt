package com.anderson.nimble.repository

import com.anderson.nimble.data.model.LoginWithEmailRequest
import com.anderson.nimble.data.model.LogoutRequest
import com.anderson.nimble.data.model.RefreshTokenRequest
import com.anderson.nimble.data.model.Registration
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
}