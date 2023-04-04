package com.triple.demo.controller

import com.triple.demo.entity.User
import com.triple.demo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
     * description : 사용자 전체 조회
     * */

    @GetMapping
    fun getAllUser(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.getAll())
    }

    /**
     * method : GET
     * uri : /user/{id}
     * description : 사용자 하나 조회
     * @Param : id
     * */

    @GetMapping("/{id}")
    fun getOneUser(@PathVariable(name = "id") id: String): ResponseEntity<User> {
        return ResponseEntity.ok().body(userService.getOne(id))
    }

    /**
     * method : POST
     * uri : /user
     * description : 사용자 생성
     * @Param : user
     * */
    @PostMapping
    fun addUser(@RequestBody user: User): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(userService.add(user))
    }

}