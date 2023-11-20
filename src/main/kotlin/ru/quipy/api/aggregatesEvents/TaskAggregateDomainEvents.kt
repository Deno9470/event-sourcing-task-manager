package ru.quipy.api.aggregatesEvents

import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

//- ID: UUID
//- name: String
//- description: String
//- status: Status
const val TASK_CREATED_EVENT = "TASK_CREATED_EVENT"
const val TASK_RENAMED_EVENT = "TASK_RENAMED_EVENT"
//const val EXECUTORS_CHANGED_EVENT = "EXECUTORS_CHANGED_EVENT"
const val EXECUTOR_ADDED_EVENT = "EXECUTOR_ADDED_EVENT"
const val EXECUTOR_REMOVED_EVENT = "EXECUTOR_REMOVED_EVENT"
const val TASK_STATUS_CHANGED_EVENT = "TASK_STATUS_CHANGED_EVENT"



@DomainEvent(name = TASK_CREATED_EVENT)
class TaskCreatedEvent(
    val taskId: UUID,
    val taskName : String,
    val description : String,
    val statusId : UUID,
    val parentProjectID : UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
        name = TASK_CREATED_EVENT,
        createdAt = createdAt,
)
@DomainEvent(name = TASK_RENAMED_EVENT)
class TaskRenamedEvent(
    val taskId: UUID,
    val oldTaskName : String,
    val newTaskName : String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_RENAMED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = EXECUTOR_ADDED_EVENT)
class ExecutorAddedEvent(
    val taskId: UUID,
    val addedUserId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = EXECUTOR_ADDED_EVENT,
    createdAt = createdAt,
)
@DomainEvent(name = EXECUTOR_REMOVED_EVENT)
class ExecutorRemovedEvent(
    val taskId: UUID,
    val removedUserId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = EXECUTOR_REMOVED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_STATUS_CHANGED_EVENT)
class TaskStatusChangedEvent(
    val taskId: UUID,
    val oldStatusId: UUID?,
    val newStatusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_STATUS_CHANGED_EVENT,
    createdAt = createdAt,
)




