package ru.quipy.logic.aggregateStates

import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

//- ID: UUID
//- name: String
//- description: String
//- status: Status.ID
//- parentProjectID : UUID
//- executors: Array(User.ID) | []

// Service's business logic
class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    lateinit var name: String
    lateinit var description: String
    lateinit var statusId: UUID
    lateinit var parentProjectID: UUID
    var executors: MutableList<UUID> = mutableListOf()


    override fun getId() = taskId

    // State transition functions which is represented by the class member function

    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        this.taskId = event.taskId
        this.name = event.taskName
        this.description = event.description
        this.parentProjectID = event.parentProjectID
    }
    @StateTransitionFunc
    fun taskRenamedApply(event: TaskRenamedEvent) {
        this.name = event.newTaskName
    }

    @StateTransitionFunc
    fun executorAddedApply(event: ExecutorAddedEvent) {
        this.executors.add(event.addedUserId)
    }
    @StateTransitionFunc
    fun executorRemovedApply(event: ExecutorRemovedEvent) {
        this.executors.remove(event.removedUserId)
    }

    @StateTransitionFunc
    fun statusChangedApply(event: TaskStatusChangedEvent) {
        this.statusId = event.newStatusId
    }
}
