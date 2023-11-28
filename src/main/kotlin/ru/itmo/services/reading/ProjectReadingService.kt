package ru.itmo.services.reading

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.itmo.entities.ProjectEntity
import ru.itmo.repositories.ProjectRepository
import java.time.LocalDateTime
import java.util.*

@Service
class ProjectReadingService(
    private val projectRepository: ProjectRepository
) : ReadingService<ProjectEntity, UUID> {

    private var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getAll() : Iterable<ProjectEntity> {
        val projects = this.projectRepository.findAll()
        this.logger.info("All projects have been read at {}", LocalDateTime.now())
        return projects
    }

    override fun getById(id: UUID) : ProjectEntity {
        val foundProject = this.projectRepository.findById(id)
            .orElseGet { throw IllegalArgumentException("No such project with id $id") }

        this.logger.info("Project $id has been read at {}", LocalDateTime.now())
        return foundProject
    }
}