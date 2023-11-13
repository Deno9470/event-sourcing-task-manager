package ru.itmo.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.itmo.entities.StatusEntity
import java.util.*

interface StatusRepository : MongoRepository<StatusEntity, UUID> {
}