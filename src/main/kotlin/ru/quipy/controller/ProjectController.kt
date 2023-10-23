package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.core.EventSourcingService
import ru.quipy.domain.Event
import ru.quipy.dto.CreateProjectDto
import ru.quipy.dto.CreateStatusDto
import ru.quipy.logic.*
import ru.quipy.logic.aggregateCommands.*
import ru.quipy.logic.aggregateStates.ColorEnum
import ru.quipy.logic.aggregateStates.ProjectAggregateState
import java.util.*

//Создайте REST API для
// создания,
// измнения и
// получения последней версии агрегатов по его ID.


@RestController
@RequestMapping("/projects")
class ProjectController(
    val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @GetMapping("")
    fun getAllProjects() : List<ProjectAggregateState> {
        throw IllegalAccessException()
    }

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: UUID) : ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

    @PostMapping("")
    fun createProject(@RequestBody createProjectDto: CreateProjectDto) : Event<ProjectAggregate> {
        return projectEsService.create {
            it.create(
                createProjectDto.projectName,
                createProjectDto.description,
                createProjectDto.creatorId
            ).first()
        }
    }

    @PatchMapping("/{projectId}")
    fun renameTask(@PathVariable projectId: UUID, @RequestBody newProjectName: String) : ProjectRenamedEvent {
        return projectEsService.update(projectId) {
            it.renameProject(newProjectName)
        }
    }

    @PostMapping("/{projectId}/participants/{userId}")
    fun addParticipant(@PathVariable projectId: UUID, @PathVariable userId: UUID) : ParticipantAddedEvent {
        return projectEsService.update(projectId) {
            it.addParticipant(userId)
        }
    }

    @DeleteMapping("/{projectId}/participants/{userId}")
    fun removeParticipant(@PathVariable projectId: UUID, @PathVariable userId: UUID) : ParticipantRemovedEvent {
        return projectEsService.update(projectId) {
            it.removeParticipant(userId)
        }
    }
    @PostMapping("/{projectId}/statuses/")
    fun createStatus(@PathVariable projectId: UUID, @RequestBody createStatusDto: CreateStatusDto) : TaskStatusCreatedEvent {
        return projectEsService.update(projectId) {
            it.createTaskStatus(
                createStatusDto.title,
                createStatusDto.color,
            )
        }
    }

    @DeleteMapping("/{projectId}/statuses/{statusId}")
    fun removeStatus(@PathVariable projectId: UUID, @PathVariable statusId: UUID) : TaskStatusRemovedEvent {
        return projectEsService.update(projectId) {
            it.removeTaskStatus(statusId)
        }
    }
}