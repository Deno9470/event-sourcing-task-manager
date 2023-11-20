package ru.quipy.logic.aggregateCommands

import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.domain.Event
import ru.quipy.logic.aggregateStates.TaskAggregateState
import java.util.*

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

//fun ProjectAggregateState.create(id: UUID, title: String, creatorId: String): ProjectCreatedEvent {
//    return ProjectCreatedEvent(
//            projectId = id,
//            title = title,
//            creatorId = creatorId,
//    )
//}



fun TaskAggregateState.createTask(taskName: String, description: String, statusId: UUID, parentProjectId: UUID): List<Event<TaskAggregate>> {
    // TODO Project existing check ???
    // TODO Put Status.Id = Created
    val taskId = UUID.randomUUID()
    return ArrayList(
        listOf(
            TaskCreatedEvent(taskId, taskName, description, statusId, parentProjectId),
            TaskStatusChangedEvent(taskId, null, statusId)
        )
    )
}

fun TaskAggregateState.renameTask(newTaskName: String) : TaskRenamedEvent {
    return TaskRenamedEvent(this.getId(),  this.name, newTaskName)
}

fun TaskAggregateState.addExecutor(addedUserId: UUID) : ExecutorAddedEvent {
    // TODO User in project check
    return ExecutorAddedEvent(this.getId(), addedUserId)
}

fun TaskAggregateState.removeExecutor(removedUserId: UUID) : ExecutorRemovedEvent {
    if (!executors.contains(removedUserId)) {
        throw IllegalArgumentException("No such user with ID in this task: $removedUserId")
    }
    return ExecutorRemovedEvent(this.getId(), removedUserId)
}

fun TaskAggregateState.changeStatus(newStatusId: UUID) : TaskStatusChangedEvent {
    // TODO Is new Status exists check

    return TaskStatusChangedEvent(this.getId(), this.statusId, newStatusId)
}
