package ru.itmo.services.subscribers

import org.springframework.stereotype.Service
import ru.itmo.entities.UserEntity
import ru.itmo.repositories.UserRepository
import ru.quipy.api.aggregates.UserAggregate
import ru.quipy.api.aggregatesEvents.UserRegisteredEvent
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct


@Service
class UserSubscriberService (
    private val subscriptionsManager: AggregateSubscriptionsManager,
    private val userRepository: UserRepository
){
    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(UserAggregate::class, "user-events-subscriber") {

            `when`(UserRegisteredEvent::class) { event ->
                val entity = UserEntity(event.userId, event.userName, event.nickname)
                userRepository.save(entity)
            }

        }
    }
}