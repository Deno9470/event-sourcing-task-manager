package ru.itmo.entities

import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.logic.aggregateStates.ColorEnum
import java.util.*
@Document("statuses")
data class StatusEntity (
    val id: UUID,
    val prev: UUID?,
    val title: String,
    val color: ColorEnum,
    val parentProjectID : UUID,
    val tasks : MutableList<UUID> = mutableListOf()
    )
