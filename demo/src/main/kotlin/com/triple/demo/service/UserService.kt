package com.triple.demo.service

import com.triple.demo.model.User
import com.triple.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun updatePoint(userId: String, point: Long) {
        var user: User = userRepository.findById(userId).get()
        user.totalPoint = user.totalPoint?.plus(point)
        userRepository.save(user)
    }

}