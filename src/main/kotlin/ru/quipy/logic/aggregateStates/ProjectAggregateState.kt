package ru.quipy.logic.aggregateStates

import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

//- ID: UUID
//- name: String
//- description: String
//- participants: Array(User.ID)
//- taskStatuses: Array(Status.ID)

// Service's business logic
class ProjectAggregateState : AggregateState<UUID, ProjectAggregate> {
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    lateinit var name: String
    lateinit var description: String
    var participants: MutableList<UUID> = mutableListOf()
    var taskStatuses: MutableMap<UUID, StatusEntity> = mutableMapOf()


    override fun getId() = projectId

    // State transition functions which is represented by the class member function

    // ProjectCreatedEvent
// ProjectRenamedEvent
// ParticipantAddedEvent
// ParticipantRemovedEvent
// TaskStatusCreatedEvent
// TaskStatusRemovedEvent

    @StateTransitionFunc
    fun projectCreatedApply(event: ProjectCreatedEvent) {
        this.projectId = event.projectId
        this.name = event.projectName
        this.description = event.description
        val createdStatus = StatusEntity(UUID.randomUUID(), null, "CREATED", ColorEnum.Black, event.projectId)
        this.taskStatuses[createdStatus.id] = createdStatus
    }

    @StateTransitionFunc
    fun projectRenamedApply(event: ProjectRenamedEvent) {
        this.name = event.newProjectName
    }

    @StateTransitionFunc
    fun participantAddedApply(event: ParticipantAddedEvent) {
        this.participants.add(event.addedUserId)
    }

    @StateTransitionFunc
    fun participantRemovedApply(event: ParticipantRemovedEvent) {
        this.participants.remove(event.removedUserId)
    }

    @StateTransitionFunc
    fun taskStatusCreatedApply(event: TaskStatusCreatedEvent) {
        this.taskStatuses[event.statusId] = StatusEntity(
            event.statusId,
            event.prev,
            event.title,
            event.color,
            this.getId()
        )
    }

    @StateTransitionFunc
    fun taskStatusRemovedApply(event: TaskStatusRemovedEvent) {
        taskStatuses.remove(event.statusId)
    }

}

data class StatusEntity(
    val id: UUID,
    val prev: UUID?,
    val title: String = "CREATED",
    val color: ColorEnum,
    var parentID : UUID
)
enum class ColorEnum {
    Black, White, Orange, Red, Pink, Cyan,
}