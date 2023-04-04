package com.triple.demo.model

import com.triple.demo.common.entity.AuditingEntity
import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.envers.Audited
import org.hibernate.envers.RelationTargetAuditMode
import javax.persistence.*

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
data class Event(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: String? = null,
    val reviewId: String? = null,
    var content: String? = null,
    @ElementCollection
    var attachedPhotoIds: List<String>? = listOf(),
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
): AuditingEntity()
