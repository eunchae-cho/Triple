package com.triple.demo.controller

import com.triple.demo.common.enums.ActionType
import com.triple.demo.common.enums.EventType
import com.triple.demo.entity.Review
import com.triple.demo.entity.ReviewAddEntity
import com.triple.demo.service.ReviewService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService
) {
    @GetMapping
    fun getAllReview(): ResponseEntity<List<Review>> {
        return ResponseEntity.ok(reviewService.getAll())
    }

    @GetMapping("/{placeId}")
    fun getAllReviewByPlaceId(@PathVariable(name = "placeId") placeId: String): ResponseEntity<List<Review>> {
        return ResponseEntity.ok(reviewService.getAllByPlaceId(placeId))
    }

    @PostMapping
    fun add(@RequestBody reviewAddEntity: ReviewAddEntity): ResponseEntity<Unit> {
        return ResponseEntity.ok(reviewService.add(reviewAddEntity))
    }
}