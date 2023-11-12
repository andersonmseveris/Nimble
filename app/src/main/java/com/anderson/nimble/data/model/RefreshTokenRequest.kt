package com.anderson.nimble.data.model

data class RefreshTokenRequest(
    var grant_type: String,
    var refresh_token: String,
    var client_id: String,
    var client_secret: String
)