package com.triple.demo.service

import com.triple.demo.common.enums.PointType
import com.triple.demo.model.Event
import com.triple.demo.entity.ReviewEntity
import com.triple.demo.model.Place
import com.triple.demo.model.Point
import com.triple.demo.model.User
import com.triple.demo.repository.EventRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val pointService: PointService
) {

    // 한 사용자는 장소마다 리뷰를 1개만 작성
    fun hasReview(reviewEntity: ReviewEntity): Boolean {
        return eventRepository.countByUserIdAndPlaceId(reviewEntity.placeId, reviewEntity.userId) > 0
    }

    fun addReview(reviewEntity: ReviewEntity) {
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
                points = makePointList(reviewEntity)
            )
        )
    }

    @Transactional
    fun modifyReview(reviewEntity: ReviewEntity) {
        var event = eventRepository.findByReviewId(reviewEntity.reviewId)
        val oldPoints = event.points

        // 새 리뷰 내용 변경
        event.action = reviewEntity.action
        event.content = reviewEntity.content
        event.attachedPhotoIds = reviewEntity.attachedPhotoIds
        event.points = event.points?.let { makePointList(reviewEntity, it) }

        // 이벤트 업데이트
        eventRepository.save(event)

        // 기존 포인트 내역 삭제
        oldPoints!!.forEach { i -> i.id?.let { pointService.deleteByIdNotBonus(it) } }
    }

    @Transactional
    fun deleteReview(reviewEntity: ReviewEntity) {
        eventRepository.deleteByReviewId(reviewEntity.reviewId)
    }

    fun sumPointByUserId(id: String): Long {
        return eventRepository.sumPointByUserId(id)
    }


    private fun makePointList(reviewEntity: ReviewEntity): List<Point> {
        var list = mutableListOf<Point>()

        // 1자 이상 텍스트 작성: 1점
        if (reviewEntity.content.isNotEmpty()) {
            list.add(
                Point(
                    score = PointType.ADD_TEXT.getPoint(),
                    comment = PointType.ADD_TEXT.getValue(),
                    scoreType = PointType.ADD_TEXT.getKey()
                )
            )
        }

        // 1장 이상 사진 첨부: 1점
        if (reviewEntity.attachedPhotoIds.isNotEmpty()) {
            list.add(
                Point(
                    score = PointType.ADD_PHOTO.getPoint(),
                    comment = PointType.ADD_PHOTO.getValue(),
                    scoreType = PointType.ADD_PHOTO.getKey()
                )
            )
        }

        // 특정 장소에 첫 리뷰 작성: 1점
        if (eventRepository.countByPlaceId(reviewEntity.placeId) == 0) {
            list.add(
                Point(
                    score = PointType.BONUS_FIRST_REVIEW.getPoint(),
                    comment = PointType.BONUS_FIRST_REVIEW.getValue(),
                    scoreType = PointType.BONUS_FIRST_REVIEW.getKey()
                )
            )
        }

        return list
    }

    private fun makePointList(reviewEntity: ReviewEntity, pointList: List<Point>): List<Point> {
        var list = pointList.filter { i -> i.scoreType == PointType.BONUS_FIRST_REVIEW.getKey() }.toMutableList()

        // 1자 이상 텍스트 작성: 1점
        if (reviewEntity.content.isNotEmpty()) {
            list.add(
                Point(
                    score = PointType.ADD_TEXT.getPoint(),
                    comment = PointType.ADD_TEXT.getValue(),
                    scoreType = PointType.ADD_TEXT.getKey()
                )
            )
        }

        // 1장 이상 사진 첨부: 1점
        if (reviewEntity.attachedPhotoIds.isNotEmpty()) {
            list.add(
                Point(
                    score = PointType.ADD_PHOTO.getPoint(),
                    comment = PointType.ADD_PHOTO.getValue(),
                    scoreType = PointType.ADD_PHOTO.getKey()
                )
            )
        }

        return list
    }

}