package com.anderson.nimble.utils

import kotlin.properties.Delegates

object TokenUtils {

    private var _accessToken = ""
    private var _tokenType = ""
    private var _expiresIn by Delegates.notNull<Int>()
    private var _refreshToken = ""
    private var _createdAt by Delegates.notNull<Long>()
    val accessToken get() = _accessToken
    val tokenType get() = _tokenType
    val expiresIn get() = _expiresIn
    val refreshToken get() = _refreshToken
    val createdAt get() = _createdAt

    fun setAccessToken(accessToken: String) {
        _accessToken = accessToken
    }

    fun setTokenType(tokenType: String) {
        _tokenType = tokenType
    }

    fun setExpiresIn(expiresIn: Int) {
        _expiresIn = expiresIn
    }

    fun setRefreshToken(refreshToken: String) {
        _refreshToken = refreshToken
    }

    fun setCreatedAt(createdAt: Long) {
        _createdAt = createdAt
    }
}