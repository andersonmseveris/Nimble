package com.anderson.nimble.data.model

data class LoginWithEmailResponse(
    val data: TokenData
)

data class TokenData(
    val id: String,
    val type: String,
    val attributes: TokenAttributes
)

data class TokenAttributes(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Long
)