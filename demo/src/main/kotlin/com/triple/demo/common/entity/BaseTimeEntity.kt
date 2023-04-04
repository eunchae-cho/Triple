package com.triple.demo.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime

abstract class BaseTimeEntity {
    @CreatedBy
    open val createdAt: LocalDateTime? = LocalDateTime.now()
    @LastModifiedBy
    open val modifiedBy: LocalDateTime? = null
}