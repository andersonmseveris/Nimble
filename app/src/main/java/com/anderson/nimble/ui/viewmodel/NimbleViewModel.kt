package com.anderson.nimble.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.nimble.data.model.loginlogout.LoginWithEmailRequest
import com.anderson.nimble.data.model.survey.SurveyDetailsResponse
import com.anderson.nimble.domain.NimbleUseCase
import com.anderson.nimble.domain.model.survey.SurveyItem
import com.anderson.nimble.utils.ClientUtils
import com.anderson.nimble.utils.TokenUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NimbleViewModel
@Inject constructor(
    private val nimbleUseCase: NimbleUseCase
) : ViewModel() {

    private val _surveyList = MutableStateFlow(emptyList<SurveyItem>())
    val surveyList: StateFlow<List<SurveyItem>> get() = _surveyList

    private val _surveyDetails = MutableStateFlow<SurveyDetailsResponse?>(null)
    val surveyDetails: StateFlow<SurveyDetailsResponse?> get() = _surveyDetails

    private val _successfulLogin = MutableStateFlow(false)
    val successfulLogin: StateFlow<Boolean> get() = _successfulLogin. asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading. asStateFlow()

    private val _accessToken =  MutableStateFlow<String>("")
    private val _tokenType =  MutableStateFlow<String>("")
    private val _expiresIn =  MutableStateFlow<Int>(0)
    private val _refreshToken =  MutableStateFlow<String>("")
    private val _createdAt =  MutableStateFlow<Long>(0L)

    suspend fun loginWithEmail(email: String, password: String) {
        val loginWithEmailRequest = LoginWithEmailRequest(
            "password",
            email,
            password,
            ClientUtils.clientId,
            ClientUtils.clientSecret
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val response = nimbleUseCase.loginWithEmail(loginWithEmailRequest)
                withContext(Dispatchers.Main) {
                    _successfulLogin.value = true

                    response.let { tokenResponse ->
                        val tokenData = tokenResponse.data
                        val attributes = tokenData.attributes

                        _accessToken.value = attributes.access_token
                        TokenUtils.setAccessToken(attributes.access_token)
                        _tokenType.value = attributes.token_type
                        TokenUtils.setTokenType(attributes.token_type)
                        _expiresIn.value = attributes.expires_in
                        TokenUtils.setExpiresIn(attributes.expires_in)
                        _refreshToken.value = attributes.refresh_token
                        TokenUtils.setRefreshToken(attributes.refresh_token)
                        _createdAt.value = attributes.created_at
                        TokenUtils.setCreatedAt(attributes.created_at)
                    }
                }
                _isLoading.value = false
            } catch (_: Exception) {
                _successfulLogin.value = false
                _isLoading.value = false
                Timber.tag("Endpoint loginWithEmail")
            }
        }.join()
    }

    suspend fun getSurvey() {
        viewModelScope.launch {
            try {
                val response = nimbleUseCase.getSurvey()
                withContext(Dispatchers.Main) {
                    response.let { survey ->
                        _surveyList.value = survey.data.toList()
                    }
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint getSurvey").e(e.message)
            }
        }.join()
    }

//    fun loginWithEmail(email: String, password: String) {
//        _isLoading.value = true
//
//        val loginWithEmailRequest = LoginWithEmailRequest(
//            "password",
//            email,
//            password,
//            ClientUtils.clientId,
//            ClientUtils.clientSecret
//        )
//        viewModelScope.launch {
//            try {
//                val response = nimbleRepository.loginWithEmail(loginWithEmailRequest)
//
//                if (response.isSuccessful) {
//                    val accessTokenResponse = response.body()
////                    _successfulLogin.value = true
////                    _isLoading.value = false
////                    accessTokenResponse?.let { tokenResponse ->
////                        val tokenData = tokenResponse.data
////                        val attributes = tokenData.attributes
////
////                        _accessToken.postValue(attributes.access_token).let { TokenUtils.setAccessToken(attributes.access_token) }
////                        _tokenType.postValue(attributes.token_type).let { TokenUtils.setTokenType(attributes.token_type) }
////                        _expiresIn.postValue(attributes.expires_in).let { TokenUtils.setExpiresIn(attributes.expires_in) }
////                        _refreshToken.postValue(attributes.refresh_token).let { TokenUtils.setRefreshToken(attributes.refresh_token) }
////                        _createdAt.postValue(attributes.created_at).let { TokenUtils.setCreatedAt(attributes.created_at) }
//                    }
//                } else {
//                    _successfulLogin.value = false
//                    _isLoading.value = false
//                    Timber.tag("Endpoint loginWithEmail")
//                }
//            } catch (e: Exception) {
//                _successfulLogin.value = false
//                _isLoading.value = false
//                Timber.tag("Endpoint loginWithEmail")
//            }
//        }
//    }

//    suspend fun refreshToken() {
//        val refreshTokenRequest = RefreshTokenRequest(
//            "refresh_token",
//            TokenUtils.refreshToken,
//            ClientUtils.clientId,
//            ClientUtils.clientSecret
//        )
//        viewModelScope.launch {
//            try {
//                val response = nimbleRepository.refreshToken(refreshTokenRequest)
//
//                if (response.isSuccessful) {
//                    val refreshTokenResponse = response.body()
//
//                    refreshTokenResponse?.let { tokenResponse ->
//                        val tokenData = tokenResponse.data
//                        val attributes = tokenData.attributes
//
//                        _accessToken.postValue(attributes.access_token).let { TokenUtils.setAccessToken(attributes.access_token) }
//                        _tokenType.postValue(attributes.token_type).let { TokenUtils.setTokenType(attributes.token_type) }
//                        _expiresIn.postValue(attributes.expires_in).let { TokenUtils.setExpiresIn(attributes.expires_in) }
//                        _refreshToken.postValue(attributes.refresh_token).let { TokenUtils.setRefreshToken(attributes.refresh_token) }
//                        _createdAt.postValue(attributes.created_at).let { TokenUtils.setCreatedAt(attributes.created_at) }
//
//                    }
//                } else {
//                    Timber.tag("Endpoint loginWithEmail")
//                }
//            } catch (e: Exception) {
//                Timber.tag("Endpoint loginWithEmail")
//            }
//        }.join()
//    }
//
//    suspend fun logout() {
//        val logoutRequest = LogoutRequest(
//            TokenUtils.accessToken,
//            ClientUtils.clientId,
//            ClientUtils.clientSecret
//        )
//        viewModelScope.launch {
//            try {
//                val response = nimbleRepository.logout(logoutRequest)
//
//                if (response.isSuccessful) {
//                    Timber.tag("Logout").e("Logout bem sucedido")
//                } else {
//                    val errorBody = response.errorBody()?.string()
//
//                    if (!errorBody.isNullOrBlank()) {
//                        try {
//                            val errorJson = JSONObject(errorBody)
//                            val errorsArray = errorJson.getJSONArray("errors")
//
//                            for (i in 0 until errorsArray.length()) {
//                                val errorObject = errorsArray.getJSONObject(i)
//                                val detail = errorObject.getString("detail")
//                                val code = errorObject.getString("code")
//
//                                Timber.tag("Error").e("Detail: $detail, Code: $code")
//                            }
//                        } catch (e: JSONException) {
//                            Timber.e(e, "Erro ao analisar JSON de erro")
//                        }
//                    } else {
//                        Timber.e("Corpo do erro vazio ou nulo")
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e, "Erro ao realizar logout")
//            }
//        }.join()
//    }
//
//    suspend fun forgotPassword() {
//        val userMail = UserMail(
//            "andersonms92@hotmail.com"
//        )
//        val forgotPassword = ForgotPasswordRequest(
//            userMail,
//            ClientUtils.clientId,
//            ClientUtils.clientSecret
//        )
//        viewModelScope.launch {
//            try{
//                val response = nimbleRepository.forgotPassword(forgotPassword)
//
//                if(response.isSuccessful){
//                    Message(response.body().toString())
//                } else {
//                    val errorBody = response.errorBody()?.string()
//
//                    if (!errorBody.isNullOrBlank()) {
//                        try {
//                            val errorJson = JSONObject(errorBody)
//                            val errorsArray = errorJson.getJSONArray("errors")
//
//                            for (i in 0 until errorsArray.length()) {
//                                val errorObject = errorsArray.getJSONObject(i)
//                                val detail = errorObject.getString("detail")
//                                val code = errorObject.getString("code")
//
//                                Timber.tag("Error").e("Detail: $detail, Code: $code")
//                            }
//                        } catch (e: JSONException) {
//                            Timber.e(e, "Erro ao analisar JSON de erro")
//                        }
//                    } else {
//                        Timber.e("Corpo do erro vazio ou nulo")
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e, "Erro ao recuperar senha")
//            }
//        }.join()
//    }
//

//    fun getSurveyDetails(id: String) {
//        viewModelScope.launch {
//            try {
//                val response = nimbleRepository.getSurveyDetails(id)
//                if(response.isSuccessful) {
//
//                    val surveyDetailsResponse = response.body()
//                    surveyDetailsResponse?.let { surveyDetails ->
//
//                        val surveyItem = surveyDetails.data
//                        val includedList = surveyDetails.included
//
//                        _surveyDetails.value = surveyDetails
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.string()
//
//                    if (!errorBody.isNullOrBlank()) {
//                        try {
//                            val errorJson = JSONObject(errorBody)
//                            val errorsArray = errorJson.getJSONArray("errors")
//
//                            for (i in 0 until errorsArray.length()) {
//                                val errorObject = errorsArray.getJSONObject(i)
//                                val detail = errorObject.getString("detail")
//                                val code = errorObject.getString("code")
//
//                                Timber.tag("Error").e("Detail: $detail, Code: $code")
//                            }
//                        } catch (e: JSONException) {
//                            Timber.e(e, "Erro ao analisar JSON de erro")
//                        }
//                    } else {
//                        Timber.e("Corpo do erro vazio ou nulo")
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e, "Erro ao recuperar detalhes das enquetes")
//            }
//        }
//    }
}