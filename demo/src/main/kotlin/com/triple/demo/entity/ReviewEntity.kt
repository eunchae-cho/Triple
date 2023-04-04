package com.triple.demo.entity

import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType

data class ReviewEntity(
    val content: String,
    val reviewId: String,
    val attachedPhotoIds : List<String>,
    val userId: String,
    val placeId: String,
    val type : EventType,
    val action : ActionType
)
