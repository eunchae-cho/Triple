package com.triple.demo.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    val userId: String? = null,
    val name: String? = null,
    val loginName: String? = null,
    val password: String? = null,
    val totalPoint: Long? = 0L
)