package com.triple.demo.entity

import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType

data class ReviewAddEntity(
    val  content: String,
    val attachedPhotoIds : List<String>,
    val userId: String,
    val placeId: String,
    val type : EventType,
    val action : ActionType
)
