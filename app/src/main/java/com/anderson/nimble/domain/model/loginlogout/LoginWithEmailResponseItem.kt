package com.anderson.nimble.domain.model.loginlogout

import com.anderson.nimble.data.model.loginlogout.LoginWithEmailResponse
import com.anderson.nimble.data.model.loginlogout.TokenLoginAttributes
import com.anderson.nimble.data.model.loginlogout.TokenLoginData

data class LoginWithEmailItem(
    val data: TokenLoginDataItem
)

data class TokenLoginDataItem(
    val id: String,
    val type: String,
    val attributes: TokenLoginAttributesItem
)

data class TokenLoginAttributesItem(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Long
)

fun TokenLoginAttributes.toLoginWithEmailAttributesItem() = TokenLoginAttributesItem(
    access_token = this.access_token,
    token_type = this.token_type,
    expires_in = this.expires_in,
    refresh_token = this.refresh_token,
    created_at = this.created_at
)

fun TokenLoginData.toLoginWithEmailItem() = TokenLoginDataItem(
    id = this.id,
    type = this.type,
    attributes = this.attributes.toLoginWithEmailAttributesItem()
)

fun LoginWithEmailResponse.toLoginWithEmailItem() = LoginWithEmailItem(
    data = data.toLoginWithEmailItem()
)