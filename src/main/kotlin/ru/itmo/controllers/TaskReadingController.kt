package ru.itmo.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.entities.TaskEntity
import ru.itmo.services.reading.TaskReadingService
import java.util.UUID

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/\${api.prefix}/\${api.currentVersion}" +
            "/tasks"]
)
class TaskReadingController(
    private val taskReadingService: TaskReadingService
) {

    @GetMapping("")
    fun getAll() : ResponseEntity<Iterable<TaskEntity>> {
        val tasks = this.taskReadingService.getAll()
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{taskId}")
    fun getById(@PathVariable taskId: UUID) : ResponseEntity<TaskEntity> {
        val task = this.taskReadingService.getById(taskId)
        return ResponseEntity.ok(task)
    }

}