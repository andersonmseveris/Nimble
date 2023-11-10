package com.anderson.nimble.di

import com.anderson.nimble.data.remote.NimbleAuthInterceptor
import com.anderson.nimble.data.remote.NimbleServiceApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiConfigurationModule {

    private fun buildClient(): OkHttpClient? {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(5, TimeUnit.MINUTES)
        builder.readTimeout(5, TimeUnit.MINUTES)
        builder.writeTimeout(5, TimeUnit.MINUTES)
        builder.addInterceptor(NimbleAuthInterceptor())
        return builder.build()
    }

    @Provides
    @Singleton
    fun retrofitInstance(): NimbleServiceApi =
        Retrofit.Builder()
            .baseUrl("https://survey-api.nimblehq.co/api/v1/")
            .client(buildClient()!!)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().disableHtmlEscaping().create()))
            .build()
            .create(NimbleServiceApi::class.java)
}