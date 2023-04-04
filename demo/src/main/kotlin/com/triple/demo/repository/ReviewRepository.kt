package com.triple.demo.repository

import com.triple.demo.entity.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository: JpaRepository<Review, String> {
    @Query("select r from Review r where r.place = ?1")
    fun findAllByPlaceId(placeId: String): List<Review>
}