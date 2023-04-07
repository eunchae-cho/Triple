package com.triple.demo.repository

import com.triple.demo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: JpaRepository<Event, String> {
    fun countByUserIdAndPlaceId(userId: String, placeId: String): Long
    fun countByPlaceId(placeId: String): Int
    fun findByReviewId(reviewId: String): Event
    fun deleteByReviewId(reviewId: String)
    @Query("select ifnull(sum(p.score), 0) from point p, event e, event_points ep " +
            "where e.id = ep.event_id and p.id = ep.points_id and e.user_id = ?1", nativeQuery = true)
    fun sumPointByUserId(userId: String): Long
}