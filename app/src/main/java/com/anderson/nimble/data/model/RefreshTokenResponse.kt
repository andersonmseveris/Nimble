package com.anderson.nimble.data.model

data class RefreshTokenResponse(
    val data: TokenRefreshData
)

data class TokenRefreshData(
    val id: String,
    val type: String,
    val attributes: TokenRefreshAttributes
)

data class TokenRefreshAttributes(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Long
)