package ru.quipy.dto

import org.springframework.web.bind.annotation.RequestBody
import java.util.*

class CreateTaskDto {
    lateinit var taskName: String
    lateinit var description: String
    lateinit var parentProjectId : UUID
}