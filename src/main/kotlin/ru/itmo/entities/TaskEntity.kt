package ru.itmo.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("tasks")
data class TaskEntity (
    var taskId: UUID,
    var name: String,
    var description: String,
    var statusId: UUID,
    var parentProjectID: UUID,
    var executors: MutableList<UUID> = mutableListOf()
)
