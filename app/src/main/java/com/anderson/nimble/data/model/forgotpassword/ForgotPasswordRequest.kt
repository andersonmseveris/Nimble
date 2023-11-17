package com.anderson.nimble.data.model.forgotpassword

data class ForgotPasswordRequest(
    var user: UserMail,
    var client_id: String,
    var client_secret: String
)

data class UserMail(
    var email: String
)