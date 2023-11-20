package ru.quipy.logic.aggregateCommands

import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.domain.Event
import ru.quipy.logic.aggregateStates.ColorEnum
import ru.quipy.logic.aggregateStates.ProjectAggregateState
import java.util.*
import kotlin.collections.ArrayList

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(name: String, description: String, creatorId: UUID): List<Event<ProjectAggregate>> {
    val projectId = UUID.randomUUID()
    return ArrayList(
        listOf(
            ProjectCreatedEvent(projectId, name, description),
            ParticipantAddedEvent(projectId, creatorId)
        )
    )
}
 fun ProjectAggregateState.renameProject(newProjectName: String) : ProjectRenamedEvent {
     return ProjectRenamedEvent(this.getId(),this.name, newProjectName)
 }

fun ProjectAggregateState.addParticipant(addedParticipantId: UUID) : ParticipantAddedEvent {
    return ParticipantAddedEvent(this.getId(), addedParticipantId)
}

fun ProjectAggregateState.removeParticipant(removedParticipantId: UUID) : ParticipantRemovedEvent {
    return ParticipantRemovedEvent(this.getId(), removedParticipantId)
}

fun ProjectAggregateState.createTaskStatus(title: String, color: ColorEnum) : TaskStatusCreatedEvent {
    if (taskStatuses.values.any { it.title == title }) {
        throw IllegalArgumentException("Status already exists: $title")
    }
    return TaskStatusCreatedEvent(UUID.randomUUID(), this.getId(), taskStatuses.keys.last(), title, color)
}

fun ProjectAggregateState.removeTaskStatus(removedStatusId: UUID) : TaskStatusRemovedEvent {
    // TODO numbers of tasks == 0 check
    if (!taskStatuses.containsKey(removedStatusId)) {
        throw IllegalArgumentException("No such status with ID: $removedStatusId")
    }
    return TaskStatusRemovedEvent(removedStatusId, this.getId())
}


