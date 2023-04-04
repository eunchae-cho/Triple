package com.triple.demo.controller

import com.triple.demo.common.enums.ActionType
import com.triple.demo.entity.ReviewEntity
import com.triple.demo.service.EventService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
) {
    @PostMapping("/events")
    fun add(@RequestBody reviewEntity: ReviewEntity): ResponseEntity<Unit> {
        return when(reviewEntity.action) {
            ActionType.ADD ->
                if (eventService.hasReview(reviewEntity)) {
                    ResponseEntity.badRequest().build()
                } else {
                    ResponseEntity.ok(eventService.addReview(reviewEntity))
                }
            else -> ResponseEntity.badRequest().build()
        }
    }
}