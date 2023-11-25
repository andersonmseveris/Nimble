package com.anderson.nimble.data.remote

import com.anderson.nimble.data.model.forgotpassword.ForgotPasswordRequest
import com.anderson.nimble.data.model.forgotpassword.ForgotPasswordResponse
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailResponse
import com.anderson.nimble.data.model.loginlogout.LogoutRequest
import com.anderson.nimble.data.model.token.RefreshTokenRequest
import com.anderson.nimble.data.model.token.RefreshTokenResponse
import com.anderson.nimble.data.model.registration.Registration
import com.anderson.nimble.data.model.survey.SurveyDetailsResponse
import com.anderson.nimble.data.model.survey.SurveyResponse
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NimbleApi {

//    Sign up
    @POST("registrations")
    suspend fun registration(
        @Body requestBody: Registration
    ): Response<ResponseBody>

//    Sign In/Out
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

//    Surveys
    @GET("surveys?page[number]=1&page[size]=2")
    suspend fun getSurvey(): Response<SurveyResponse>

    @GET("surveys/{id}")
    suspend fun getSurveyDetails(@Path("id") id: String): Response<SurveyDetailsResponse>
}