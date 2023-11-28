package ru.itmo.services.reading

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.itmo.entities.StatusEntity
import ru.itmo.repositories.StatusRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class StatusReadingService(
    private val statusRepository: StatusRepository
) : ReadingService<StatusEntity, UUID> {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getAll() : Iterable<StatusEntity> {
        val statuses = this.statusRepository.findAll()
        this.logger.info("All project tasks have been read at {}", LocalDateTime.now())
        return statuses
    }

    override fun getById(id: UUID): StatusEntity {
        val foundStatus = this.statusRepository.findById(id)
            .orElseGet { throw IllegalArgumentException("No such status with id $id") }

        this.logger.info("Task $id has been read at {}", LocalDateTime.now())
        return foundStatus
    }

    fun getAllByProjectId(projectId: UUID) : Iterable<StatusEntity> {
        val statusesOfProject = this.statusRepository.getAllByParentProjectID(projectId)

        this.logger.info("All statuses of project $projectId have been read at {}", LocalDateTime.now())
        return statusesOfProject
    }

    fun getByProjectIdAndId(projectId: UUID, statusId: UUID): StatusEntity {
        val foundStatus = this.getById(statusId)
        if (foundStatus.parentProjectID != projectId) {
            throw IllegalArgumentException("Can not access to not correspond status!")
        }

        return foundStatus
    }


}