package com.triple.demo.repository

import com.triple.demo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: JpaRepository<Event, String> {
    @Query("select count(*) from Event where place = ?1 and user = ?2")
    fun countByUserIdAndPlaceId(placeId: String, userId: String): Int

    @Query("select count(*) from Event where place = ?1")
    fun countByPlaceId(placeId: String): Int
}