package com.triple.demo.repository

import com.triple.demo.model.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface PointRepository: JpaRepository<Point, Long> {
    @Modifying
    @Transactional
    @Query("delete from Point where id = ?1 and scoreType != 'BONUS'")
    fun deletePointByIdAndScoreTypeIsNotBONUS(id: Long)
}