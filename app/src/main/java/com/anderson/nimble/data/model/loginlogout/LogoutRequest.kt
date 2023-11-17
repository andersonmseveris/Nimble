package com.anderson.nimble.data.model.loginlogout

data class LogoutRequest(
    var token: String,
    var client_id: String,
    var client_secret: String
)
