package com.triple.demo.model

import com.triple.demo.common.entity.BaseTimeEntity
import com.triple.demo.common.enums.ActionType
import javax.persistence.*

@Entity
data class PointHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "event_id")
    val event: Event? = null,
    @Enumerated(EnumType.STRING)
    val action: ActionType? = ActionType.ADD,
    val point: Long,
    @ElementCollection
    val reason: List<String>? = listOf()
): BaseTimeEntity()
