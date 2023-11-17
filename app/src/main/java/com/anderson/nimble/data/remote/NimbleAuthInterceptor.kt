package com.anderson.nimble.data.remote

import com.anderson.nimble.utils.TokenUtils
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import timber.log.Timber

class NimbleAuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        try {
            val requestBuilder = request.newBuilder()
                .header("Authorization",  "${TokenUtils.tokenType} ${TokenUtils.accessToken}")

            request = requestBuilder.build()
        } catch (e: Exception) {
            Timber.tag("AuthInterceptor").e(e.message)
        }

        return chain.proceed(request    )
    }
}