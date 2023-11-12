package com.anderson.nimble.data.model

data class Registration(
    var user: UserData,
    var client_id: String,
    var client_secret: String
)
data class UserData(
    var email: String,
    var name: String,
    var password: String,
    var password_confirmation: String
)