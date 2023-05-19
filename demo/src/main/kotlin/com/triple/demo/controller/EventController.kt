package com.triple.demo.controller

import com.triple.demo.common.enums.ActionType
import com.triple.demo.entity.ReviewEntity
import com.triple.demo.service.EventService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/events")
class EventController(
    private val eventService: EventService
) {

    /**
     * method : POST
     * uri : /events
     * description : 이벤트 저장/수정/삭제
     * @Param : 리뷰 객체 (ReviewEntity)
     * */
    @ApiOperation(value = "포인트 적립 API")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 400, message = "Bad Request"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 500, message = "Internal Server Error"),
    )
    @PostMapping
    fun occurEvent(@RequestBody reviewEntity: ReviewEntity): ResponseEntity<Unit> {
        return when(reviewEntity.action) {
            ActionType.ADD ->
                if (eventService.hasReview(reviewEntity)) {
                    ResponseEntity.badRequest().build()
                } else {
                     ResponseEntity.ok(eventService.addReview(reviewEntity))
                }

            ActionType.MOD -> ResponseEntity.ok(eventService.modifyReview(reviewEntity))

            ActionType.DELETE -> ResponseEntity.ok(eventService.deleteReview(reviewEntity))
        }
    }

    /**
     * method : POST
     * uri : /events/{userId}
     * description : 개인 누적포인트 조회
     * @Param : userId
     * */
    @ApiOperation(value = "사용자 포인트 조회 API")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 400, message = "Bad Request"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 500, message = "Internal Server Error"),
    )
    @GetMapping("/{userId}")
    fun getTotalPoint(@PathVariable("userId") id: String): ResponseEntity<Long> {
        return ResponseEntity.ok(eventService.sumPointByUserId(id))
    }
}
