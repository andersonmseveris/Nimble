package com.anderson.nimble.data.remote

import com.anderson.nimble.data.model.forgotpassword.ForgotPasswordRequest
import com.anderson.nimble.data.model.forgotpassword.ForgotPasswordResponse
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailResponse
import com.anderson.nimble.data.model.loginlogout.LogoutRequest
import com.anderson.nimble.data.model.token.RefreshTokenRequest
import com.anderson.nimble.data.model.token.RefreshTokenResponse
import com.anderson.nimble.data.model.registration.Registration
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

    @POST("passwords")
    suspend fun forgotPassword(
        @Body requestBody: ForgotPasswordRequest
    ): Response<ForgotPasswordResponse>
}