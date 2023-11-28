package ru.itmo.services.subscribers

import org.springframework.stereotype.Service
import ru.itmo.entities.TaskEntity
import ru.itmo.repositories.TaskRepository
import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.api.aggregatesEvents.*
import javax.annotation.PostConstruct
import ru.quipy.streams.AggregateSubscriptionsManager


@Service
class TaskSubscriberService (
    private val subscriptionsManager: AggregateSubscriptionsManager,
    private val taskRepository: TaskRepository
) {

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "task-events-subscriber") {

            `when`(TaskCreatedEvent::class) {event ->
                val entity = TaskEntity(event.taskId, event.taskName, event.description, event.statusId, event.parentProjectID)
                taskRepository.save(entity)
            }

            `when`(TaskRenamedEvent::class) { event ->
                val entity = taskRepository.findById(event.taskId).get()
                entity.name = event.newTaskName
                taskRepository.save(entity)
            }

            `when`(ExecutorAddedEvent::class) { event ->
                val entity = taskRepository.findById(event.taskId).get()
                entity.executors.add(event.addedUserId)
                taskRepository.save(entity)
            }

            `when`(ExecutorRemovedEvent::class) { event ->
                val entity = taskRepository.findById(event.taskId).get()
                entity.executors.remove(event.removedUserId)
                taskRepository.save(entity)
            }

            `when`(TaskStatusChangedEvent::class) { event ->
                val entity = taskRepository.findById(event.taskId).get()
                entity.statusId = event.newStatusId
                taskRepository.save(entity)
            }

        }
    }
}