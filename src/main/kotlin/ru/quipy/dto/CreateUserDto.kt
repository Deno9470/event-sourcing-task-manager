package ru.quipy.dto

import lombok.Value

@Value
class CreateUserDto {
    lateinit var nickname: String
    lateinit var name: String
    lateinit var password: String
}