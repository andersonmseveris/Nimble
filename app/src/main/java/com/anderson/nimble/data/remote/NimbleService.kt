package com.anderson.nimble.data.remote

import javax.inject.Inject

class NimbleService @Inject constructor(private val nimbleApi: NimbleServiceApi) {
//    suspend fun loginWithEmail() {
//        val loginWithEmail = LoginWithEmail(
//            "password",
//            "your_email@example.com",
//            "12345678",
//            "ofzl-2h5ympKa0WqqTzqlVJUiRsxmXQmt5tkgrlWnOE",
//            "lMQb900L-mTeU-FVTCwyhjsfBwRCxwwbCitPob96cuU"
//        ).toString()
//
//        return withContext(Dispatchers.IO) {
//            try {
//                val response: Response<ResponseBody> = nimbleApi.loginWithEmail(
//                    loginWithEmail
//                )
//
//                if (response.isSuccessful) {
//                    response.body()
//                } else {
//                    val errorBody = response.errorBody()
//                    throw Exception("Erro na API: $errorBody")
//                }
//            } catch (e: HttpException) {
//                throw Exception("Erro na chamada da API HTTP: ${e.message()}")
//            } catch (e: Exception) {
//                throw Exception("Erro na chamda da API: ${e.message}")
//            }
//        }
//    }
}