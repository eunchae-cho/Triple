package com.triple.demo.service

import com.triple.demo.repository.PointRepository
import org.springframework.stereotype.Service

@Service
class PointService(
    private val pointRepository: PointRepository
) {
    fun deleteById(id: Long) {
        pointRepository.deleteById(id)
    }

    fun deleteByIdNotBonus(id: Long) {
        pointRepository.deletePointByIdAndScoreTypeIsNotBONUS(id)
    }
}