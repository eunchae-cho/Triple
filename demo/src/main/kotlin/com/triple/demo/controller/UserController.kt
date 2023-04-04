package com.triple.demo.controller

import com.triple.demo.model.User
import com.triple.demo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    /**
     * method : GET
     * uri : /user
     * description : 전체 사용자 조회
     * */
    @GetMapping
    fun findAll(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.findAll())
    }

    /**
     * method : GET
     * uri : /user/{id}
     * description : 사용자 조회
     * @Param : 사용자 아이디
     * */
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.findById(id))
    }

    /**
     * method : GET
     * uri : /user/{id}/point
     * description : 사용자 포인트 조회
     * @Param : 사용자 아이디
     * */
    @GetMapping("/{id}/point")
    fun getPointById(@PathVariable("id") id: String): ResponseEntity<Long> {
        return ResponseEntity.ok(userService.findPointById(id))
    }

}