package com.anderson.nimble.data.remote

import com.anderson.nimble.data.model.LoginWithEmailRequest
import com.anderson.nimble.data.model.LoginWithEmailResponse
import com.anderson.nimble.data.model.LogoutRequest
import com.anderson.nimble.data.model.RefreshTokenRequest
import com.anderson.nimble.data.model.RefreshTokenResponse
import com.anderson.nimble.data.model.Registration
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface NimbleServiceApi {

    @POST("registrations")
    suspend fun registration(
        @Body requestBody: Registration
    ): Response<ResponseBody>

    @POST("oauth/token")
    suspend fun loginWithEmail(
        @Body requestBody: LoginWithEmailRequest
    ): Response<LoginWithEmailResponse>

    @POST("oauth/token")
    suspend fun refreshToken(
        @Body requestBody: RefreshTokenRequest
    ): Response<RefreshTokenResponse>

    @POST("oauth/revoke")
    suspend fun logout(
        @Body requestBody: LogoutRequest
    ): Response<ResponseBody>
}