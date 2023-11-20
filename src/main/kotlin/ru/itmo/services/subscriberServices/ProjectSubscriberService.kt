package ru.itmo.services.subscriberServices

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.itmo.entities.ProjectEntity
import ru.itmo.repositories.ProjectRepository
import ru.quipy.api.aggregates.ProjectAggregate
import ru.quipy.api.aggregatesEvents.*
import ru.quipy.projections.AnnotationBasedProjectEventsSubscriber
import javax.annotation.PostConstruct
import ru.quipy.streams.AggregateSubscriptionsManager
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent


//@AggregateSubscriber(
//    aggregateClass = ProjectAggregate::class, subscriberName = "project-event-stream"
//)
@Service
class ProjectSubscriberService (
    private val subscriptionsManager: AggregateSubscriptionsManager,
    private val projectRepository: ProjectRepository
) {

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(ProjectAggregate::class, "project-events-subscriber") {
            `when`(ProjectCreatedEvent::class) {event ->
                val entity = ProjectEntity(event.projectId, event.projectName, event.description, mutableListOf(),  mutableListOf())
                projectRepository.save(entity)
            }
            `when`(ProjectRenamedEvent::class) { event ->
                val entity = projectRepository.findById(event.projectId).get()
                entity.name = event.newProjectName
                projectRepository.save(entity)
            }
            `when`(ParticipantAddedEvent::class) { event ->
                val entity = projectRepository.findById(event.projectId).get()
                entity.participants.add(event.addedUserId)
                projectRepository.save(entity)
            }
            `when`(ParticipantRemovedEvent::class) { event ->
                val entity = projectRepository.findById(event.projectId).get()
                entity.participants.remove(event.removedUserId)
                projectRepository.save(entity)
            }
            `when`(TaskStatusCreatedEvent::class) { event ->
                val entity = projectRepository.findById(event.projectId).get()
                entity.taskStatuses.add(event.statusId)
                projectRepository.save(entity)
            }
            `when`(TaskStatusRemovedEvent::class) { event ->
                val entity = projectRepository.findById(event.projectId).get()
                entity.taskStatuses.remove(event.statusId)
                projectRepository.save(entity)
            }
        }
    }
}