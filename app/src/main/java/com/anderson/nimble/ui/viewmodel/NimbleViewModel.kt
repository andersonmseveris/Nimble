package com.anderson.nimble.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.nimble.data.model.LoginWithEmailRequest
import com.anderson.nimble.data.model.LogoutRequest
import com.anderson.nimble.data.model.RefreshTokenRequest
import com.anderson.nimble.data.model.Registration
import com.anderson.nimble.data.model.UserData
import com.anderson.nimble.repository.NimbleRepository
import com.anderson.nimble.utils.ClientUtils
import com.anderson.nimble.utils.TokenUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NimbleViewModel
@Inject constructor(
//    private val nimbleUseCase: NimbleUseCase
    private val nimbleRepository: NimbleRepository
) : ViewModel() {

    private val _successfulLogin = MutableLiveData<String>()
    val successfulLogin: LiveData<String> = _successfulLogin

    private val _accessToken = MutableLiveData<String>()
    private val _tokenType = MutableLiveData<String>()
    private val _expiresIn = MutableLiveData<Int>()
    private val _refreshToken = MutableLiveData<String>()
    private val _createdAt = MutableLiveData<Long>()
    suspend fun registration() {
        viewModelScope.launch {
            try {

                val userData = UserData(
                    email = "testeemail@hotmail.com",
                    name = "Anderson",
                    password = "123456",
                    password_confirmation = "123456"
                )
                val registrationData = Registration(
                    user = userData,
                    ClientUtils.clientId,
                    ClientUtils.clientSecret
                )

                val response: Response<ResponseBody> =
                    nimbleRepository.registration(registrationData)

                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        responseBody.toString()
                    }
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint").e(e.message)
            }
        }
    }

    suspend fun loginWithEmail() {
        val loginWithEmailRequest = LoginWithEmailRequest(
            "password",
            "andersonms92@hotmail.com",
            "123456",
            ClientUtils.clientId,
            ClientUtils.clientSecret
        )
        viewModelScope.launch {
            try {
                val response = nimbleRepository.loginWithEmail(loginWithEmailRequest)

                if (response.isSuccessful) {
                    val accessTokenResponse = response.body()

                    accessTokenResponse?.let { tokenResponse ->
                        val tokenData = tokenResponse.data
                        val attributes = tokenData.attributes

                        _accessToken.postValue(attributes.access_token).let { TokenUtils.setAccessToken(attributes.access_token) }
                        _tokenType.postValue(attributes.token_type).let { TokenUtils.setTokenType(attributes.token_type) }
                        _expiresIn.postValue(attributes.expires_in).let { TokenUtils.setExpiresIn(attributes.expires_in) }
                        _refreshToken.postValue(attributes.refresh_token).let { TokenUtils.setRefreshToken(attributes.refresh_token) }
                        _createdAt.postValue(attributes.created_at).let { TokenUtils.setCreatedAt(attributes.created_at) }
                    }
                } else {
                    Timber.tag("Endpoint loginWithEmail")
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint loginWithEmail")
            }
        }.join()
    }

    suspend fun refreshToken() {
        val refreshTokenRequest = RefreshTokenRequest(
            "refresh_token",
            TokenUtils.refreshToken,
            ClientUtils.clientId,
            ClientUtils.clientSecret
        )
        viewModelScope.launch {
            try {
                val response = nimbleRepository.refreshToken(refreshTokenRequest)

                if (response.isSuccessful) {
                    val refreshTokenResponse = response.body()

                    refreshTokenResponse?.let { tokenResponse ->
                        val tokenData = tokenResponse.data
                        val attributes = tokenData.attributes

                        _accessToken.postValue(attributes.access_token).let { TokenUtils.setAccessToken(attributes.access_token) }
                        _tokenType.postValue(attributes.token_type).let { TokenUtils.setTokenType(attributes.token_type) }
                        _expiresIn.postValue(attributes.expires_in).let { TokenUtils.setExpiresIn(attributes.expires_in) }
                        _refreshToken.postValue(attributes.refresh_token).let { TokenUtils.setRefreshToken(attributes.refresh_token) }
                        _createdAt.postValue(attributes.created_at).let { TokenUtils.setCreatedAt(attributes.created_at) }

                    }
                } else {
                    Timber.tag("Endpoint loginWithEmail")
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint loginWithEmail")
            }
        }.join()
    }

    suspend fun logout() {
        val logoutRequest = LogoutRequest(
            TokenUtils.accessToken,
            ClientUtils.clientId,
            ClientUtils.clientSecret
        )
        viewModelScope.launch {
            try {
                val response = nimbleRepository.logout(logoutRequest)

                if (response.isSuccessful) {
                    Timber.tag("Logout").e("Logout bem sucedido")
                } else {
                    val errorBody = response.errorBody()?.string()

                    if (!errorBody.isNullOrBlank()) {
                        try {
                            val errorJson = JSONObject(errorBody)
                            val errorsArray = errorJson.getJSONArray("errors")

                            for (i in 0 until errorsArray.length()) {
                                val errorObject = errorsArray.getJSONObject(i)
                                val detail = errorObject.getString("detail")
                                val code = errorObject.getString("code")

                                Timber.tag("Error").e("Detail: $detail, Code: $code")
                            }
                        } catch (e: JSONException) {
                            Timber.e(e, "Erro ao analisar JSON de erro")
                        }
                    } else {
                        Timber.e("Corpo do erro vazio ou nulo")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Erro ao realizar logout")
            }
        }.join()
    }

}