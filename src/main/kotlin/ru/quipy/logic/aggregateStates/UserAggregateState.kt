package ru.quipy.logic.aggregateStates

import ru.quipy.api.aggregates.UserAggregate
import ru.quipy.api.aggregatesEvents.UserRegisteredEvent
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

//- ID: UUID
//- nickname: String
//- name: String
//- password: String

// Service's business logic
class UserAggregateState : AggregateState<UUID, UserAggregate> {
    private lateinit var userId: UUID
    var createdAt: Long = System.currentTimeMillis()
    lateinit var name : String
    lateinit var nickname : String
    lateinit var password : String

    override fun getId() = userId

    // State transition functions which is represented by the class member function

    @StateTransitionFunc
    fun userRegisteredApply(event: UserRegisteredEvent) {
        this.userId = event.userId
        this.name = event.name
        this.nickname = event.nickname
        this.password = event.password
    }
}


