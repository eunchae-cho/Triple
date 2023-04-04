package com.triple.demo.service

import com.triple.demo.entity.Place
import com.triple.demo.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class PlaceService(
    private val placeRepository: PlaceRepository
) {
    fun getAll(): List<Place> {
        return placeRepository.findAll()
    }

    fun getOne(id: String): Place {
        return placeRepository.findById(id).get()
    }

    fun add(place: Place) {
        placeRepository.save(place)
    }

    fun delete(id: String) {
        placeRepository.deleteById(id)
    }
}