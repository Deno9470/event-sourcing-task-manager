package ru.itmo.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID
@Document("projects")
data class ProjectEntity(
        var projectId: UUID,
        var name: String,
        var description: String,
        var participants: MutableList<UUID>,
        var taskStatuses: MutableMap<UUID, StatusEntity>
)
