package com.triple.demo.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Place(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    val placeId: String? = null,
    val title: String? = null,
    val content: String? = null
)