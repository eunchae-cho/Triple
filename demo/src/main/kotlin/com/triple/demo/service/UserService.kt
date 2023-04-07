package com.triple.demo.service

import com.triple.demo.model.User
import com.triple.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun saveUser(user: User) {
        userRepository.save(user)
    }
}