package ru.itmo.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.entities.UserEntity
import ru.itmo.services.reading.UserReadingService
import java.util.*

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/\${api.prefix}/\${api.currentVersion}" +
            "/users"]
)
class UserReadingController(
    private val userReadingService: UserReadingService
) {

    @GetMapping("")
    fun getAll() : ResponseEntity<Iterable<UserEntity>> {
        val users = this.userReadingService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{taskId}")
    fun getById(@PathVariable taskId: UUID) : ResponseEntity<UserEntity> {
        val user = this.userReadingService.getById(taskId)
        return ResponseEntity.ok(user)
    }
}