package com.triple.demo.entity

import com.triple.demo.common.enums.ActionType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class PointHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "review_id")
    val review: Review? = null,
    @Enumerated(EnumType.STRING)
    val action: ActionType? = ActionType.ADD,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val point: Long,
    @ElementCollection
    val reason: List<String>? = listOf()
)
