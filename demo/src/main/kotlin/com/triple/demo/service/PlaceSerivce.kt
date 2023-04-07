package com.triple.demo.service

import com.triple.demo.model.Place
import com.triple.demo.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class PlaceSerivce(
    private val placeRepository: PlaceRepository
) {
    fun savePlace(place: Place) {
        placeRepository.save(place)
    }
}