package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.core.EventSourcingService
import ru.quipy.dto.CreateTaskDto
import ru.quipy.logic.aggregateCommands.*
import ru.quipy.logic.aggregateStates.TaskAggregateState
import java.util.*

//Создайте REST API для
// создания,
// измнения
// получения последней версии агрегатов по его ID.


@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {
    @GetMapping("")
    fun getAllTasks() : List<TaskAggregateState> {
        throw IllegalAccessException()
    }

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: UUID) : TaskAggregateState? {
        return taskEsService.getState(taskId)
    }

    @PostMapping("")
    fun createTask(@RequestBody createTaskDto: CreateTaskDto) : TaskCreatedEvent {
        return taskEsService.create {
            it.createTask(
                taskName = createTaskDto.taskName,
                description = createTaskDto.description,
                statusId = UUID(0,0),    // TODO Implement static StatusId Created
                parentProjectId = createTaskDto.parentProjectId
            )
        }
    }
    
    @PatchMapping("/{taskId}")
    fun renameTask(@PathVariable taskId: UUID, @RequestBody newTaskName : String) : TaskRenamedEvent {
        return taskEsService.update(taskId) {
            it.renameTask(newTaskName)
        }
    }

    @PostMapping("/{taskId}/executors/{userId}")
    fun addExecutor(@PathVariable taskId: UUID, @PathVariable userId : UUID) : ExecutorAddedEvent {
        return taskEsService.update(taskId) {
            it.addExecutor(userId)
        }
    }

    @DeleteMapping("/{taskId}/executors/{userId}")
    fun removeExecutor(@PathVariable taskId: UUID,  @PathVariable userId : UUID) : ExecutorRemovedEvent {
        return taskEsService.update(taskId) {
            it.removeExecutor(userId)
        }
    }
    @PatchMapping("/{taskId}/status")
    fun changeStatus(@PathVariable taskId : UUID, @RequestBody statusId : UUID) : TaskStatusChangedEvent {
        return taskEsService.update(taskId) {
            it.changeStatus(statusId)
        }
    }


}