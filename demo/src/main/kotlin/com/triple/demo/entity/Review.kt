package com.triple.demo.entity

import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType
import org.hibernate.annotations.GenericGenerator
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
data class Review(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    val reviewId: String? = null,
    val content: String? = null,
    @ElementCollection
    val attachedPhotoIds: List<String>? = listOf(),
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User? = null,
    @OneToOne
    @JoinColumn(name = "place_id")
    val place: Place? = null,
    @Enumerated(EnumType.STRING)
    val type: EventType? = EventType.REVIEW,
    @Enumerated(EnumType.STRING)
    val action: ActionType? = null
    )
