package ru.itmo.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.itmo.entities.TaskEntity
import java.util.*

interface TaskRepository : MongoRepository<TaskEntity, UUID> {
}