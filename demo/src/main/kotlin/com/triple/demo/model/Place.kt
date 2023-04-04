package com.triple.demo.model

import com.triple.demo.common.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Place(
    @Id
    override val id: String? = null,
    val title: String? = null,
    val content: String? = null
): BaseEntity()