package ru.quipy.logic.aggregateCommands

import ru.quipy.api.*
import ru.quipy.api.aggregatesEvents.UserRegisteredEvent
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.aggregateStates.UserAggregateState
import java.util.*

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String, creatorId: String): ProjectCreatedEvent {
    return ProjectCreatedEvent(
            projectId = id,
            title = title,
            creatorId = creatorId,
    )
}

fun UserAggregateState.registerUser(userId: UUID, name: String, nickname: String, password: String): UserRegisteredEvent {
    // TODO Uniq check
    // + DB Mongo
    // + service level
    //
    return UserRegisteredEvent(userId, nickname, name, password)
}
