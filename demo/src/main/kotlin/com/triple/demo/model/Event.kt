package com.triple.demo.model

import com.triple.demo.common.entity.BaseEntity
import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.envers.Audited
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
@Audited
data class Event(
    @Id
    override val id: String? = null,
    val reviewId: String? = null,
    var content: String? = null,
    @ElementCollection
    val attachedPhotoIds: List<String>? = listOf(),
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User? = null,
    @OneToOne
    @JoinColumn(name = "place_id")
    val place: Place? = null,
    @Enumerated(EnumType.STRING)
    val type: EventType? = null,
    @Enumerated(EnumType.STRING)
    var action: ActionType? = null,
    // 획득 포인트
    var point: Long? = 0L
): BaseEntity()
