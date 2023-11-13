package ru.itmo.repositories


import org.springframework.data.mongodb.repository.MongoRepository
import ru.itmo.entities.UserEntity
import java.util.*

interface UserRepository : MongoRepository<UserEntity, UUID> {
    fun findByNickname(nickname: String) : UserEntity
}