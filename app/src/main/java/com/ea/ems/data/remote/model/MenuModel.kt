package com.ea.ems.data.remote.model

import com.ea.ems.domain.model.MenuInfo
import com.google.firebase.firestore.PropertyName
import java.util.*

data class MenuModel(
    @get:PropertyName("image_url")
    @set:PropertyName("image_url")
    var imageUrl: String? = null,
    @get:PropertyName("created_at")
    @set:PropertyName("created_at")
    var createdAt: Date? = null
)

fun MenuModel.toInfo() = MenuInfo(
    imageUrl = this.imageUrl,
    createdAt = this.createdAt
)
