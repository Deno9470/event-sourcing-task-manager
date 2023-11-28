package ru.itmo.services.reading

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.itmo.entities.UserEntity
import ru.itmo.repositories.UserRepository
import java.time.LocalDateTime
import java.util.*

@Service // TODO: @Transactional
class UserReadingService(
    private var userRepository: UserRepository
) : ReadingService<UserEntity, UUID> {

    private var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getAll(): Iterable<UserEntity> {
        val users = this.userRepository.findAll()
        this.logger.info("All users have been read at {}", LocalDateTime.now())
        return users
    }

    override fun getById(id: UUID): UserEntity {
        val foundUser = this.userRepository.findById(id)
            .orElseGet { throw IllegalArgumentException("Can not find any user with id: $id") }

        this.logger.info("User $id has been read at {}", LocalDateTime.now())
        return foundUser;
    }

}