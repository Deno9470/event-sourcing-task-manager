package ru.itmo.services.reading

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.itmo.entities.TaskEntity
import ru.itmo.repositories.TaskRepository
import java.time.LocalDateTime
import java.util.*

@Service
class TaskReadingService(
    private val taskRepository: TaskRepository
) : ReadingService<TaskEntity, UUID> {

    private var logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun getAll(): Iterable<TaskEntity> {
        val tasks = this.taskRepository.findAll()
        this.logger.info("All users have been read at {}", LocalDateTime.now())
        return tasks
    }

    override fun getById(id: UUID): TaskEntity {
        val foundTask = this.taskRepository.findById(id)
            .orElseGet { throw IllegalArgumentException("No such task with id $id.") }

        this.logger.info("Task $id has been read at {}", LocalDateTime.now())
        return foundTask
    }


}