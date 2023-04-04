package com.triple.demo.repository

import com.triple.demo.entity.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository: JpaRepository<Place, String>