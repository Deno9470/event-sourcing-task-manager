package ru.quipy.api.aggregatesEvents

import ru.quipy.api.aggregates.UserAggregate
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val USER_REGISTERED_EVENT = "USER_REGISTERED_EVENT"

// API
@DomainEvent(name = USER_REGISTERED_EVENT)
class UserRegisteredEvent(
        val userId: UUID,
        val nickname: String,
        val userName: String,
        val password: String,
        createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
        name = USER_REGISTERED_EVENT,
        createdAt = createdAt,
)
