package com.ea.ems.data.remote.model

import com.ea.ems.domain.model.UserInfo

data class UserModel(
    val email: String,
    val token: String
)

fun UserModel.toInfo() = UserInfo(
    email = this.email,
    token = this.token
)
