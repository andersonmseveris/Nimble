package com.anderson.nimble.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.nimble.data.model.LoginWithEmail
import com.anderson.nimble.data.model.Registration
import com.anderson.nimble.data.model.UserData
import com.anderson.nimble.repository.NimbleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
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

//    fun loginWithEmail() {
//        viewModelScope.launch {
//            try {
//                nimbleUseCase.loginWithEmail()
//            } catch (_: Exception) {
//                TODO()
//            }
//        }
//    }

    suspend fun loginWithEmail() {
        val loginWithEmail = LoginWithEmail(
            "password",
            "andersonms92@hotmail.com",
            "123456",
            "ofzl-2h5ympKa0WqqTzqlVJUiRsxmXQmt5tkgrlWnOE",
            "lMQb900L-mTeU-FVTCwyhjsfBwRCxwwbCitPob96cuU"
        )
        viewModelScope.launch {
            try {
                val response = nimbleRepository.loginWithEmail(loginWithEmail)

                if (response.isSuccessful) {
                    val accessTokenResponse = response.body()

                    accessTokenResponse?.let { tokenResponse ->
                        val tokenData = tokenResponse.data
                        val attributes = tokenData.attributes

                        val accessToken = attributes.access_token
                        val tokenType = attributes.token_type
                        val expiresIn = attributes.expires_in
                        val refreshToken = attributes.refresh_token
                        val createdAt = attributes.created_at

                    }
                } else {
                    Timber.tag("Endpoint loginWithEmail")
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint loginWithEmail")
            }
        }
    }

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
                    client_id = "ofzl-2h5ympKa0WqqTzqlVJUiRsxmXQmt5tkgrlWnOE",
                    client_secret = "lMQb900L-mTeU-FVTCwyhjsfBwRCxwwbCitPob96cuU"
                )

                val response: Response<ResponseBody> =
                    nimbleRepository.registration(registrationData)

                if (response.isSuccessful){
                    response.body()?.let { responseBody ->
                        responseBody.toString()
                    }
                }
            } catch (e: Exception) {
                Timber.tag("Endpoint").e(e.message)
            }
        }
    }
}