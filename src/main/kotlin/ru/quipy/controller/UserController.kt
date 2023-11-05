package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.aggregates.UserAggregate
import ru.quipy.api.aggregatesEvents.UserRegisteredEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.dto.CreateUserDto
import ru.quipy.logic.aggregateCommands.registerUser
import ru.quipy.logic.aggregateStates.UserAggregateState
import java.util.*

//Создайте REST API для
// создания,
// измнения
// получения последней версии агрегатов по его ID.


@RestController
@RequestMapping("/users")
class UserController(
    val userEsService: EventSourcingService<UUID, UserAggregate, UserAggregateState>
) {

    @GetMapping("/")
    fun getAllUsers() : List<UserAggregateState> {
        throw IllegalAccessException()
    }

    @PostMapping("/")
    fun createUser(@RequestBody createUserDto: CreateUserDto) : UserRegisteredEvent {
        return userEsService.create { it.registerUser(UUID.randomUUID(), createUserDto.nickname, createUserDto.name, createUserDto.password) }
    }
    // TODO DTO
}

