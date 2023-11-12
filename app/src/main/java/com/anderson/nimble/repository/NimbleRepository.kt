package com.anderson.nimble.repository

import com.anderson.nimble.data.model.LoginWithEmail
import com.anderson.nimble.data.model.Registration
import com.anderson.nimble.data.remote.NimbleService
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
        requestBody: LoginWithEmail
    ) = nimbleServiceApi.loginWithEmail(
        requestBody
    )
}