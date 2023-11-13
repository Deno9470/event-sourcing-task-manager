package ru.itmo.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.itmo.entities.ProjectEntity
import java.util.UUID

interface ProjectRepository : MongoRepository<ProjectEntity, UUID> {
}