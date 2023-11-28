package ru.itmo.services.subscribers

import org.springframework.stereotype.Service
import ru.itmo.entities.StatusEntity
import ru.itmo.repositories.StatusRepository
import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.api.aggregates.TaskAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct


@Service
class StatusSubscriberService (
    private val subscriptionsManager: AggregateSubscriptionsManager,
    private val statusRepository: StatusRepository
){
    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(ProjectAggregate::class, "status-project-events-subscriber") {

            `when`(TaskStatusCreatedEvent::class) { event ->
                val entity = StatusEntity(event.statusId, event.prev, event.title, event.color, event.projectId)
                statusRepository.save(entity)
            }

            `when`(TaskStatusRemovedEvent::class) { event ->
                statusRepository.deleteById(event.statusId)
            }
        }

        subscriptionsManager.createSubscriber(TaskAggregate::class, "status-events-subscriber") {

            `when`(TaskStatusChangedEvent::class) { event ->
                if (event.oldStatusId != null) {
                    val oldStatus = statusRepository.findById(event.oldStatusId).get()
                    oldStatus.tasks.remove(event.taskId)
                    statusRepository.save(oldStatus)
                }

                val newStatus = statusRepository.findById(event.newStatusId).get()
                newStatus.tasks.add(event.taskId)
                statusRepository.save(newStatus)
            }
        }
    }
}