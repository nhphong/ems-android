package com.ea.ems.data.remote.model

import com.ea.ems.domain.model.MenuInfo

data class MenuModel(
    var imagePath: String = "",
    var createdAt: Long = 0
)

fun MenuModel.toInfo() = MenuInfo(
    imagePath = this.imagePath,
    createdAt = this.createdAt
)
