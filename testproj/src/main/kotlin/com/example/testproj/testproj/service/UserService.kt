package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.User
import com.example.testproj.testproj.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository){
    fun getUsers() : List<User>{ //이게 맞는지 ResponseEntity가 맞는지?
        val users = userRepository.findAll()
        return users
    }

    fun setUser(user : User): String{
        try {
            userRepository.save(user)
        }catch(e:Exception){
            return e.toString()
        }
        return "200"
    }

    fun getUserNameByUId(uid: String) : String{
        return "1111"
    }
}