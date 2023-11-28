package ru.itmo.services.reading

interface ReadingService<T, ID> {
    fun getAll(): Iterable<T>

    fun getById(id: ID) : T

    // TODO: getById(ids: Iterable<ID>)
    // TODO: findById(id : ID)
    // TODO: findById(ids : Iterable<ID>)
}