package com.anderson.nimble.data.model.`login/logout`

data class LoginWithEmailRequest(
    var grant_type: String,
    var email: String,
    var password: String,
    var client_id: String,
    var client_secret: String
)