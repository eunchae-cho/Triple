package com.triple.demo.controller

import com.triple.demo.entity.Place
import com.triple.demo.service.PlaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/place")
class PlaceController(
    private val placeService: PlaceService
) {
    /**
     * method : GET
     * uri : /place
     * description : 장소 전체 조회
     * */
    @GetMapping
    fun getAllPlace(): ResponseEntity<List<Place>> {
        return ResponseEntity.ok(placeService.getAll())
    }

    /**
     * method : GET
     * uri : /place/{id}
     * description : 장소 하나 조회
     * @Param : id
     * */
    @GetMapping("/{id}")
    fun getOneUser(@PathVariable(name = "id") id: String): ResponseEntity<Place> {
        return ResponseEntity.ok().body(placeService.getOne(id))
    }

    /**
     * method : POST
     * uri : /user
     * description : 장소 생성
     * @Param : place
     * */
    @PostMapping
    fun addPlace(@RequestBody place: Place): ResponseEntity<Unit> {
        return ResponseEntity.ok(placeService.add(place))
    }
}