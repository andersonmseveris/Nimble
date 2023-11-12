package com.anderson.nimble.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import timber.log.Timber

class NimbleAuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        try {
            val requestBuilder = request.newBuilder()
                .header("Authorization", "")

            val requestBody = request.body()
            val requestBodyString = requestBody?.let { body ->
                val buffer = Buffer()
                body.writeTo(buffer)
                buffer.readUtf8()
            }
            request = requestBuilder.build()
        } catch (e: Exception) {
            Timber.tag("AuthInterceptor").e(e.message)
        }

        return chain.proceed(request    )
    }
}