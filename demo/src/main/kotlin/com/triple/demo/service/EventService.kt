package com.triple.demo.service

import com.triple.demo.model.Event
import com.triple.demo.entity.ReviewEntity
import com.triple.demo.model.Place
import com.triple.demo.model.User
import com.triple.demo.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val userService: UserService
) {

    // 한 사용자는 장소마다 리뷰를 1개만 작성
    fun hasReview(reviewEntity: ReviewEntity): Boolean {
        return eventRepository.countByUserIdAndPlaceId(reviewEntity.placeId, reviewEntity.userId) > 0
    }

    fun addReview(reviewEntity: ReviewEntity) {

        val point = calculatePoint(reviewEntity)

        // 포인트 저장
        userService.updatePoint(reviewEntity.userId, point)

        // 이벤트 저장
        eventRepository.save(
            Event(
                reviewId = reviewEntity.reviewId,
                content = reviewEntity.content,
                attachedPhotoIds = reviewEntity.attachedPhotoIds,
                user = User(id = reviewEntity.userId),
                place = Place(id = reviewEntity.placeId),
                type = reviewEntity.type,
                action = reviewEntity.action,
                point = point
            )
        )
    }

    fun modifyReview(reviewEntity: ReviewEntity) {
        var event = eventRepository.findByReviewId(reviewEntity.reviewId)
        event.action = reviewEntity.action
        event.content = reviewEntity.content
        event.attachedPhotoIds = reviewEntity.attachedPhotoIds

        // 이벤트 업데이트
        eventRepository.save(event)
    }

    fun deleteReview(reviewEntity: ReviewEntity) {
        eventRepository.deleteByReviewId(reviewEntity.reviewId)
    }

    // 리뷰 보상 점수 계산
    private fun calculatePoint(reviewEntity: ReviewEntity): Long {
        var point = 0L

        // 1자 이상 텍스트 작성: 1점
        if (reviewEntity.content.isNotEmpty()) point += 1
        // 1장 이상 사진 첨부: 1점
        if (reviewEntity.attachedPhotoIds.isNotEmpty()) point += 1
        // 특정 장소에 첫 리뷰 작성: 1점
        if (eventRepository.countByPlaceId(reviewEntity.placeId) == 0) point += 1

        return point
    }

}