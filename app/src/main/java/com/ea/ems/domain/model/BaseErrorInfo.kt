package com.ea.ems.domain.model

data class BaseErrorInfo(
    val code: Int,
    override val message: String
) : Throwable(message)
