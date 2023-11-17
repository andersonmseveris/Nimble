package com.anderson.nimble.data.model.`login/logout`

data class LoginWithEmailResponse(
    val data: TokenLoginData
)

data class TokenLoginData(
    val id: String,
    val type: String,
    val attributes: TokenLoginAttributes
)

data class TokenLoginAttributes(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Long
)