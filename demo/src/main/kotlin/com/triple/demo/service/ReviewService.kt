package com.triple.demo.service

import com.triple.demo.entity.Review
import com.triple.demo.entity.ReviewAddEntity
import com.triple.demo.repository.PlaceRepository
import com.triple.demo.repository.ReviewRepository
import com.triple.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val placeRepository: PlaceRepository,
    private val userRepository: UserRepository
) {

    fun getAll(): List<Review> {
        return reviewRepository.findAll()
    }
    fun getAllByPlaceId(placeId: String): List<Review> {
        return reviewRepository.findAllByPlaceId(placeId)
    }

    fun add(reviewAddEntity: ReviewAddEntity) {

        reviewRepository.save(
            Review(
            content = reviewAddEntity.content,
            attachedPhotoIds = reviewAddEntity.attachedPhotoIds,
            user = userRepository.findById(reviewAddEntity.userId).get(),
            place = placeRepository.findById(reviewAddEntity.placeId).get(),
            type = reviewAddEntity.type,
            action = reviewAddEntity.action
            )
        )
    }
}