package com.anderson.nimble.data.remote

import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface NimbleServiceApi {

    @POST("oauth/token")
    suspend fun loginWithEmail(
        @Body requestBody: String
    ): Response<ResponseBody>
}