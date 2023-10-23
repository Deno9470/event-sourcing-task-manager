package ru.quipy.dto

import org.springframework.web.bind.annotation.RequestBody
import java.util.*

class CreateProjectDto {
    lateinit var projectName: String
    lateinit var description: String
    lateinit var creatorId: UUID
}