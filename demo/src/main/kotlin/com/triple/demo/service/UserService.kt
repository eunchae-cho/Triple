package com.triple.demo.service

import com.triple.demo.entity.User
import com.triple.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun getOne(id: String): User {
        return userRepository.findById(id).get()
    }

    fun add(user: User) {
        userRepository.save(user)
    }

    fun delete(id: String) {
        userRepository.deleteById(id)
    }

}