package com.triple.demo.repository

import com.triple.demo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: JpaRepository<Event, String> {
    fun countByUserIdAndPlaceId(placeId: String, userId: String): Int
    fun countByPlaceId(placeId: String): Int
    fun findByReviewId(reviewId: String): Event
    fun deleteByReviewId(reviewId: String)
}