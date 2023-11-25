package com.anderson.nimble.data.remote

import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailResponse
import com.anderson.nimble.data.model.survey.SurveyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class NimbleService @Inject constructor(private val nimbleApi: NimbleApi) {
    suspend fun loginWithEmail(loginWithEmailRequest: LoginWithEmailRequest): LoginWithEmailResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = nimbleApi.loginWithEmail(loginWithEmailRequest)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    val errorBody = response.errorBody()
                    throw Exception("Erro na API: $errorBody")
                }
            } catch (e: HttpException) {
                throw Exception("Erro na chamada da API HTTP: ${e.message()}")
            } catch (e: Exception) {
                throw Exception("Erro na chamda da API: ${e.message}")
            }
        }
    }

    suspend fun getSurvey(): SurveyResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = nimbleApi.getSurvey()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    val errorBody = response.errorBody()
                    throw Exception("Erro na API: $errorBody")
                }
            } catch (e: HttpException) {
                throw Exception("Erro na chamada da API HTTP: ${e.message()}")
            } catch (e: Exception) {
                throw Exception("Erro na chamda da API: ${e.message}")
            }
        }
    }
}