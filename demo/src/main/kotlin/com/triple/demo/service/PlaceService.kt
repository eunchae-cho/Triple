package com.triple.demo.service

import com.triple.demo.model.Place
import com.triple.demo.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeRepository: PlaceRepository
) {
    fun findAll(): List<Place> {
        return placeRepository.findAll()
    }

    fun findById(id: String): Place {
        return placeRepository.findById(id).get()
    }

    fun save(place: Place) {
        placeRepository.save(place)
    }

    fun delete(id: String) {
        placeRepository.deleteById(id)
    }
}