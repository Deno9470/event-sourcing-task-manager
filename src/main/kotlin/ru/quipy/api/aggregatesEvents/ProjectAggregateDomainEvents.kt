package ru.quipy.api.aggregatesEvents

import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.logic.aggregateStates.ColorEnum
import java.util.*

//- ID: UUID
//- name: String
//- description: String
//- status: Status
const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
const val PROJECT_RENAMED_EVENT = "PROJECT_RENAMED_EVENT"
const val PARTICIPANT_ADDED_EVENT = "PARTICIPANT_ADDED_EVENT"
const val PARTICIPANT_REMOVED_EVENT = "PARTICIPANT_REMOVED_EVENT"
const val TASK_STATUS_CREATED_EVENT = "TASK_STATUS_CREATED_EVENT"
const val TASK_STATUS_REMOVED_EVENT = "TASK_STATUS_REMOVED_EVENT"

// ProjectCreatedEvent
// ParticipantAddedEvent
// ParticipantRemovedEvent
// ProjectRenamedEvent
// TaskStatusCreatedEvent
// TaskStatusRemovedEvent


//TODO "CREATED" Status???
@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
    val projectId: UUID,
    val projectName : String,
    val description : String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = PROJECT_CREATED_EVENT,
        createdAt = createdAt,
)
@DomainEvent(name = PROJECT_RENAMED_EVENT)
class ProjectRenamedEvent(
    val projectId: UUID,
    val oldProjectName : String,
    val newProjectName : String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PROJECT_RENAMED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = PARTICIPANT_ADDED_EVENT)
class ParticipantAddedEvent(
    val projectId: UUID,
    val addedUserId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PARTICIPANT_ADDED_EVENT,
    createdAt = createdAt,
)
@DomainEvent(name = PARTICIPANT_REMOVED_EVENT)
class ParticipantRemovedEvent(
    val projectId: UUID,
    val removedUserId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PARTICIPANT_REMOVED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_STATUS_CREATED_EVENT)
class TaskStatusCreatedEvent(
    val statusId: UUID,
    val projectId: UUID,
    val prev: UUID,
    val title: String = "CREATED",
    val color: ColorEnum,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_STATUS_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_STATUS_REMOVED_EVENT)
class TaskStatusRemovedEvent(
    val statusId: UUID,
    val projectId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_STATUS_REMOVED_EVENT,
    createdAt = createdAt,
)



