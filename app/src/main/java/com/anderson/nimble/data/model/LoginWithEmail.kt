package com.anderson.nimble.data.model

import com.google.gson.GsonBuilder

data class LoginWithEmail(
   var grant_type: String,
   var email: String,
   var password: String,
   var client_id: String,
   var client_secret: String
) {
   override fun toString(): String {
      return GsonBuilder().disableHtmlEscaping().create().toJson(this, LoginWithEmail::class.java)
   }
}
