package com.triple.demo.model

import com.triple.demo.common.entity.BaseEntity
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
    @Id
    override var id: String? = null,
    var name: String? = null,
    var loginName: String? = null,
    var password: String? = null,
    var totalPoint: Long? = 0L
): BaseEntity()