package ru.quipy.dto

import org.springframework.web.bind.annotation.RequestBody
import ru.quipy.logic.aggregateStates.ColorEnum

class CreateStatusDto {
    lateinit var title: String
    lateinit var color: ColorEnum
}