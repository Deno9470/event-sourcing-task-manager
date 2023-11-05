package ru.quipy.logic.aggregateCommands

import ru.quipy.api.aggregatesEvents.*
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



fun TaskAggregateState.createTask(taskName: String, description: String, statusId: UUID, parentProjectId: UUID): TaskCreatedEvent {
    // TODO Project existing check ???
    // TODO Put Status.Id = Created
    return TaskCreatedEvent(taskId = UUID.randomUUID(), taskName, description, statusId, parentProjectId)
}

fun TaskAggregateState.renameTask(newTaskName: String) : TaskRenamedEvent {
    return TaskRenamedEvent(this.getId(),  this.name, newTaskName)
}

fun TaskAggregateState.addExecutor(addedUserId: UUID) : ExecutorAddedEvent {
    // TODO User in project check
    return ExecutorAddedEvent(this.getId(), addedUserId)
}

fun TaskAggregateState.removeExecutor(removedUserId: UUID) : ExecutorRemovedEvent {
    // TODO User in task check
    return ExecutorRemovedEvent(this.getId(), removedUserId)
}

fun TaskAggregateState.changeStatus(newStatusId: UUID) : TaskStatusChangedEvent {
    // TODO Is new Status exists check

    return TaskStatusChangedEvent(this.getId(), this.statusId, newStatusId)
}
